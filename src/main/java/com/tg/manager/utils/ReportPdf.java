package com.tg.manager.utils;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.tg.manager.model.*;

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

    public static void reportIsAptPdf(Set<DisplayTableModel> listStudent){
        Document documentpdf = new Document();
        try {
            Font font = FontFactory.getFont(FontFactory.TIMES, 32, Font.NORMAL, Color.RED);
            String filePath = System.getProperty("user.home") + "/Downloads/relatorio_apto.pdf";
            PdfWriter.getInstance(documentpdf, new FileOutputStream(filePath));
            documentpdf.open();
            Paragraph paragraphTest = new Paragraph("RELÁTORIO DE ALUNOS APTOS", font);
            paragraphTest.setAlignment(Element.ALIGN_CENTER);
            paragraphTest.setSpacingAfter(50);
            documentpdf.add(paragraphTest);
            for(DisplayTableModel student : listStudent){
                if(student.getIsApt()) {
                    Font font2 = FontFactory.getFont(FontFactory.TIMES, 20, Font.NORMAL, Color.BLACK);
                    Paragraph paragraphTest1 = new Paragraph("Nome aluno: " + student.getStudent().getName(), font2);
                    paragraphTest1.setSpacingAfter(10);
                    documentpdf.add(paragraphTest1);
                    Paragraph paragraphTest2 = new Paragraph("Email aluno: " + student.getStudent().getFatecEmail(), font2);
                    paragraphTest2.setSpacingAfter(10);
                    documentpdf.add(paragraphTest2);
                    Paragraph paragraphTestHast2 = new Paragraph("==============================================");
                    paragraphTestHast2.setSpacingBefore(40);
                    paragraphTestHast2.setSpacingAfter(50);
                    documentpdf.add(paragraphTestHast2);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        documentpdf.close();
    }

    public static void reportCertified(Set<AdvisorModel> listAdivisor){
        Document documentpdf = new Document();
        try {
            Font font = FontFactory.getFont(FontFactory.TIMES, 32, Font.NORMAL, Color.RED);
            String filePath = System.getProperty("user.home") + "/Downloads/relatorio_certificados.pdf";
            PdfWriter.getInstance(documentpdf, new FileOutputStream(filePath));
            documentpdf.open();
            Paragraph paragraphTest = new Paragraph("RELÁTORIO DE CERTIFICADOS", font);
            paragraphTest.setAlignment(Element.ALIGN_CENTER);
            paragraphTest.setSpacingAfter(50);
            documentpdf.add(paragraphTest);
            for(AdvisorModel advisorModel : listAdivisor){
                Font font2 = FontFactory.getFont(FontFactory.TIMES, 20, Font.NORMAL, Color.BLACK);
                Paragraph paragraphTest1 = new Paragraph("Nome do Orientador: " + advisorModel.getName(), font2);
                paragraphTest1.setSpacingAfter(10);
                documentpdf.add(paragraphTest1);
                Paragraph paragraphTestHast1 = new Paragraph("==============================================");
                paragraphTestHast1.setSpacingBefore(40);
                paragraphTestHast1.setSpacingAfter(30);
                documentpdf.add(paragraphTestHast1);
                if(!AdvisorModel.studentApt(advisorModel.getId()).isEmpty()) {
                    for (StudentModel student : AdvisorModel.studentApt(advisorModel.getId())){
                        Paragraph paragraphTest2 = new Paragraph("Email aluno: " + student.getName());
                        paragraphTest2.setSpacingAfter(10);
                        documentpdf.add(paragraphTest2);
                        Paragraph paragraphTest3 = new Paragraph("Email Fatec: " + student.getFatecEmail());
                        paragraphTest3.setSpacingAfter(10);
                        documentpdf.add(paragraphTest3);
                        Paragraph paragraphTest4 = new Paragraph("Tipo de TG: " + student.getTypeTg(student.getId()));
                        paragraphTest4.setSpacingAfter(10);
                        documentpdf.add(paragraphTest4);
                    }
                }
                Paragraph paragraphTestHast2 = new Paragraph("==============================================");
                paragraphTestHast2.setSpacingBefore(40);
                paragraphTestHast2.setSpacingAfter(50);
                documentpdf.add(paragraphTestHast2);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        documentpdf.close();
    }
}
