package com.github.kfang;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ApachePoi {

    public static void main(String[] args) {
        try {
            InputStream is = ApachePoi.class.getResourceAsStream("/DonationForm.docx");
            XWPFDocument doc = new XWPFDocument(is);

            doc.getParagraphs().forEach((paragraph) -> {
                paragraph.getRuns().forEach((run) -> {
                    String text = run.text();

                    if (text.contains("<<name>>")) {
                        text = text.replace("<<name>>", "Kevin Fang");
                    }

                    if (text.contains("<<amount>>")) {
                        text = text.replace("<<amount>>", "$100");
                    }

                    run.setText(text,0);
                });
            });

            doc.getTables().forEach((table) -> {
                String cellText = table.getRow(0).getCell(0).getText();
                if (cellText.equals("<<transactions>>")) {
                    for (int i = 0; i < 12; i++) {
                        XWPFTableRow row = table.createRow();

                        XWPFTableCell date = row.getCell(0);
                        date.setText(Integer.toString(i));

                        XWPFTableCell fund = row.getCell(1);
                        if (fund == null) {
                            fund =  row.createCell();
                        }
                        fund.setText("FUND X");

                        XWPFTableCell amount = row.getCell(2);
                        if (amount == null) {
                            amount =  row.createCell();
                        }
                        amount.setText("290.90");
                    }
                    table.removeRow(0);
                }
            });

            OutputStream os = new FileOutputStream("output.docx");
            doc.write(os);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
