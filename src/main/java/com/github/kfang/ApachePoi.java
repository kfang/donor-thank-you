package com.github.kfang;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApachePoi {

    private static File writeReceipt(XWPFDocument doc, SalesReceiptGroup group) throws IOException {
        doc.getParagraphs().forEach((paragraph) -> {
            paragraph.getRuns().forEach((run) -> {
                String text = run.text();

                if (text.contains("<<name>>")) {
                    text = text.replace("<<name>>", group.getName());
                }

                if (text.contains("<<amount>>")) {
                    text = text.replace("<<amount>>", "$" + group.getTotalAmount());
                }

                run.setText(text,0);
            });
        });

        doc.getTables().forEach((table) -> {
            String cellText = table.getRow(0).getCell(0).getText();
            if (cellText.equals("<<transactions>>")) {
                for (SalesReceipt r: group.getReceipts()) {
                    XWPFTableRow row = table.createRow();

                    XWPFTableCell date = row.getCell(0);
                    date.setText(r.getDate());

                    XWPFTableCell fund = row.getCell(1);
                    if (fund == null) {
                        fund =  row.createCell();
                    }
                    fund.setText(r.getAccount());

                    XWPFTableCell amount = row.getCell(2);
                    if (amount == null) {
                        amount =  row.createCell();
                    }
                    amount.setText(Double.toString(r.getAmount()));
                }
                table.removeRow(0);
            }
        });

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        doc.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        System.out.println("generated docx for " + group.getName());

        String filename = "./output/" + group.getName() + ".pdf";
        FileOutputStream os = new FileOutputStream(filename);
        IConverter converter = LocalConverter.builder().build();
        converter.convert(in).as(DocumentType.DOCX).to(os).as(DocumentType.PDF).execute();
        os.close();

        System.out.println("wrote " + filename);
        return new File(filename);
    }

    public static void writeReceipts(File templateFile, List<SalesReceiptGroup> groups) {
        ArrayList<File> files = new ArrayList<File>();

        groups.forEach((group) -> {
            try {
                InputStream templateIS = new FileInputStream(templateFile);
                XWPFDocument template = new XWPFDocument(templateIS);
                File f = writeReceipt(template, group);
                files.add(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        PDFMergerUtility ut = new PDFMergerUtility();
        ut.setDestinationFileName("./output/__merged.pdf");

        files.forEach((f) -> {
            try {
                ut.addSource(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        try {
            ut.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
