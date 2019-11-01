package com.github.kfang;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ApachePoi {

    public static void main(String[] args) {
        try {
            InputStream is = ApachePoi.class.getResourceAsStream("/test.docx");
            XWPFDocument doc = new XWPFDocument(is);

            doc.getParagraphs().forEach((paragraph) -> {
                paragraph.getRuns().forEach((run) -> {
                    String text = run.text();
                    if (text.contains("block")) {
                        String newText = text.replace("block", "-");
                        run.setText(newText,0);
                    }
                });
            });

            OutputStream os = new FileOutputStream("output.docx");
            doc.write(os);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
