package com.cynick.bookmagic.util.html2markdown;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Testbed {
  public static void main(String[] args) {
    URL url;
    try {
      String html = "<ul>\n" +
              "  <li>\n" +
              "    <p>Add: live templates starting with <code>.</code></p>\n" +
              "    <table>\n" +
              "      <thead>\n" +
              "        <tr><th> Element       </th><th> Abbreviation    </th><th> Expansion                                               </th></tr>\n" +
              "      </thead>\n" +
              "      <tbody>\n" +
              "        <tr><td> Abbreviation  </td><td> <code>.abbreviation</code> </td><td> <code>*[]:</code>                                                 </td></tr>\n" +
              "        <tr><td> Code fence    </td><td> <code>.codefence</code>    </td><td> ``` ... ```                                       </td></tr>\n" +
              "        <tr><td> Explicit link </td><td> <code>.link</code>         </td><td> <code>[]()</code>                                                  </td></tr>\n" +
              "      </tbody>\n" +
              "    </table>\n" +
              "  </li>\n" +
              "</ul>";

      url = new URL("http://jsoup.org/");
      String parsedText = HTML2Md.convert(html, "UTF-8");
      System.out.println(parsedText);

      // test parse local html file
//            String pathFile = "test.html";
//            File f = new File(pathFile);
//            String parsedFileText = HTML2Md.convertFile(f, "gbk");
//            System.out.println(parsedFileText);

      System.out.println("done");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}