package com.shuiyujie.generator.test;

import com.shuiyujie.generator.utils.Constants;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * created by shui 2017/8/19
 */
public class HtmlGeneratorClient {

    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            // 指定模板数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 获取创建的模板
            Template template = configuration.getTemplate("test.ftl");

            // 创建数据模型
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("user","Yujie Shui");
            Map<String,Object> latest = new HashMap<String,Object>();
            params.put("latestProduct", latest);
            latest.put("url", "products/greenmouse.html");
            latest.put("name", "green mouse");

            // 将模板和数据合并 输出到console
            Writer out = new OutputStreamWriter(System.out);
            template.process(params,out);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
