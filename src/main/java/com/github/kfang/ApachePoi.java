package com.github.kfang;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

public class ApachePoi {

    private static void writeReceipt(XWPFDocument doc, SalesReceiptGroup group) throws IOException {
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

        String filename = "./output/" + group.getName() + ".docx";
        OutputStream os = new FileOutputStream(filename);
        doc.write(os);
        System.out.println("wrote " + filename);
    }

    public static void writeReceipts(File templateFile, List<SalesReceiptGroup> groups) {
        groups.forEach((group) -> {
            try {
                InputStream templateIS = new FileInputStream(templateFile);
                XWPFDocument template = new XWPFDocument(templateIS);
                writeReceipt(template, group);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
