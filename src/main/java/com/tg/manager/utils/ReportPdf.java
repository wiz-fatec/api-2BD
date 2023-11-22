package com.tg.manager.utils;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.tg.manager.model.StudentModel;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.ToDoModel;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;

public class ReportPdf {

    public static void reportPdf(Set<StudentModel> listStudent){
        Document documentpdf = new Document();
        try {
            Font font = FontFactory.getFont(FontFactory.TIMES, 32, Font.NORMAL, Color.BLUE);
            String filePath = System.getProperty("user.home") + "/Downloads/relatorio.pdf";
            PdfWriter.getInstance(documentpdf, new FileOutputStream(filePath));
            documentpdf.open();
            Paragraph paragraphTest = new Paragraph("RELÁTORIO GERAL", font);
            paragraphTest.setAlignment(Element.ALIGN_CENTER);
            paragraphTest.setSpacingAfter(50);
            documentpdf.add(paragraphTest);

            for(StudentModel student : listStudent){
                Font font2 = FontFactory.getFont(FontFactory.TIMES, 20, Font.NORMAL, Color.BLACK);
                Paragraph paragraphTestTitle1 = new Paragraph("Dados do Aluno", font2);
                paragraphTestTitle1.setSpacingBefore(20);
                paragraphTestTitle1.setSpacingAfter(20);
                documentpdf.add(paragraphTestTitle1);
                Paragraph paragraphTest1 = new Paragraph("Nome aluno: " + student.getName() );
                paragraphTest1.setSpacingAfter(10);
                documentpdf.add(paragraphTest1);
                Paragraph paragraphTest2 = new Paragraph("Turma: " + student.getTypeTg(student.getId()));
                paragraphTest2.setSpacingAfter(10);
                documentpdf.add(paragraphTest2);
                Paragraph paragraphTest3 = new Paragraph("Orientador: " + student.getAdvisor(student.getAdvisorId()));
                paragraphTest3.setSpacingAfter(20);
                documentpdf.add(paragraphTest3);
                if(!student.getTodo(student.getId()).isEmpty()) {
                    Paragraph paragraphTestTitle2 = new Paragraph("Entregas", font2);
                    paragraphTestTitle2.setSpacingBefore(20);
                    paragraphTestTitle2.setSpacingAfter(20);
                    documentpdf.add(paragraphTestTitle2);
                    for(ToDoModel todo : student.getTodo(student.getId())) {
                        Paragraph paragraphTest4 = new Paragraph("Entrega: " + SubmitModel.getSubmitId(todo.getIdIssue()));
                        paragraphTest4.setSpacingAfter(10);
                        documentpdf.add(paragraphTest4);
                        Paragraph paragraphTest5 = new Paragraph("Nota: " + todo.getNote());
                        paragraphTest5.setSpacingAfter(10);
                        documentpdf.add(paragraphTest5);
                    }
                }
                Paragraph paragraphTestHast2 = new Paragraph("==============================================");
                paragraphTestHast2.setSpacingBefore(40);
                paragraphTestHast2.setSpacingAfter(50);
                documentpdf.add(paragraphTestHast2);
                Paragraph paragraphTestSpace2 = new Paragraph("");
                documentpdf.add(paragraphTestSpace2);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        documentpdf.close();
    }
}
