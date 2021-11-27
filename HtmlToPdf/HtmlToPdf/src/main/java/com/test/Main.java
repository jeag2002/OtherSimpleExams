/**
 * Copyright (c) 2013 Elaxy Financial Software & Solutions Gmbh & Co. KG
 * Alle Rechte vorbehalten.
 *
 * @file   Main.java
 */
package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Main {

  /*
  public static void main(String[] args) throws Exception {
      try (OutputStream os = new FileOutputStream("c:\\workspaces\\workEclipse\\TestHTMLTOPDF.pdf")) {
          PdfRendererBuilder builder = new PdfRendererBuilder();
          builder.useFastMode();
          builder.withUri("c:/workspaces/workEclipse/HtmlToPdf/html/Test.html");
          builder.toStream(os);
          builder.run();
      }
  }*/



  public static void main(String[] args)
  {
    try {
      // HTML file - Input
      File inputHTML = new File("D:/workspaces/workEclipse_1/HtmlToPdf/HtmlToPdf/html/Test.html");
      // Converted PDF file - Output
      String outputPdf = "D:/workspaces/workEclipse_1/TestHTMLTOPDF.pdf";
      Main htmlToPdf = new Main();
      //create well formed HTML
      org.w3c.dom.Document doc = htmlToPdf.createWellFormedHtml(inputHTML);
      System.out.println("Starting conversion to PDF...");
      htmlToPdf.xhtmlToPdf(doc, outputPdf);
    } catch (IOException e) {
      System.out.println("Error while converting HTML to PDF " + e.getMessage());
      e.printStackTrace();
    }
  }


  // Creating well formed document
  private org.w3c.dom.Document createWellFormedHtml(File inputHTML) throws IOException {
    Document document = Jsoup.parse(inputHTML, "UTF-8");
    document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
    System.out.println("HTML parsing done...");
    return new W3CDom().fromJsoup(document);
  }

  private void xhtmlToPdf(org.w3c.dom.Document doc, String outputPdf) throws IOException {
    // base URI to resolve future resources
    String baseUri = FileSystems.getDefault()
                .getPath("C:/", "workspaces/workEclipse/HtmlToPdf/", "html")
                .toUri()
                .toString();
    OutputStream os = new FileOutputStream(outputPdf);
    PdfRendererBuilder builder = new PdfRendererBuilder();
    builder.withUri(outputPdf);
    builder.toStream(os);
    // add external font
    builder.useFont(new File("c:/workspaces/workEclipse/HtmlToPdf/fonts/PRISTINA.TFF"), "PRISTINA");
    builder.withW3cDocument(doc, baseUri);
    builder.run();
    System.out.println("PDF creation completed");
    os.close();
  }



}

