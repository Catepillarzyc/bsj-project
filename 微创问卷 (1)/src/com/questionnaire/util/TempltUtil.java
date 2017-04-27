package com.questionnaire.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TempltUtil
{
  public static final String WORD_TEMPLATE = "templt/question.ftl";
  public static final String QUESTION_DOC = "templt/question.doc";
  public static Logger log = Logger.getLogger(TempltUtil.class);

  public static void createWord(Map<String, Object> dataMap, String wordPath)
  {
    try
    {
      Configuration configuration = new Configuration();

      configuration.setDefaultEncoding("UTF-8");

      File file = new File(wordPath);
      configuration.setDirectoryForTemplateLoading(file);

      Template template = configuration.getTemplate("templt/question.ftl");

      File outFile = new File(wordPath + "templt/question.doc");

      if (!outFile.getParentFile().exists()) {
        outFile.getParentFile().mkdirs();
      }

      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

      template.process(dataMap, out);

      out.flush();
      out.close();
    } catch (Exception e) {
      log.error("生成word文档(WordUtil)出错：【msg：" + e.getMessage() + "】,文件名：" + "templt/question.ftl");
    }
  }
}