//package com.joshua.StockManagementSystem.joseph_impl.domain;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.w3c.tidy.Tidy;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.nio.file.FileSystems;
//
//public class TransactionPDFCreator {
//  private final SpringTemplateEngine springTemplateEngine;
//
//  @Autowired
//  public TransactionPDFCreator(SpringTemplateEngine springTemplateEngine) {
//    this.springTemplateEngine = springTemplateEngine;
//  }
//
//  private void create(Context context){
//    String htmlContentToRender = springTemplateEngine.process("TransactionPDF", context);
//    try {
//      String xHtml = xhtmlConvert(htmlContentToRender);
//      ITextRenderer renderer = new ITextRenderer();
//
//      String baseURL = FileSystems.getDefault()
//              .getPath("src","main","resources","templates")
//              .toUri()
//              .toURL()
//              .toString();
//
//      renderer.setDocumentFromString(xHtml,baseURL);
//      renderer.layout();
//
//    } catch (UnsupportedEncodingException | MalformedURLException e) {
//      e.printStackTrace();
//    }
//
//  }
//
//  private String xhtmlConvert(String html) throws UnsupportedEncodingException {
//    Tidy tidy = new Tidy();
//    tidy.setInputEncoding("UTF-8");
//    tidy.setOutputEncoding("UTF-8");
//    tidy.setXHTML(true);
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    tidy.parseDOM(inputStream, outputStream);
//    return outputStream.toString("UTF-8");
//  }
//}
