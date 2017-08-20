package com.shuiyujie.generator.task;

import com.shuiyujie.generator.application.ApplicationContext;
import com.shuiyujie.generator.application.ApplicationTask;
import com.shuiyujie.generator.model.ColumnInfo;
import com.shuiyujie.generator.model.TableInfo;
import com.shuiyujie.generator.model.VO;
import com.shuiyujie.generator.test.Entity;
import com.shuiyujie.generator.test.Property;
import com.shuiyujie.generator.test.PropertyType;
import com.shuiyujie.generator.utils.Constants;
import com.shuiyujie.generator.utils.FileUtil;
import com.sun.tools.jdi.VoidValueImpl;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.tools.jconsole.Tab;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by shui 2017/8/20
 */
public class VOTask extends InitTask {

    private static File javaFile = null;

    public boolean doInternale() throws Exception{

        TableInfo tableInfo = (TableInfo) contexts.get("tableInfo");

        Configuration configuration = new Configuration();

        try {
            // 指定数据源
            configuration.setDirectoryForTemplateLoading(new File(Constants.TEMPLATE_PATH));
            configuration.setObjectWrapper(new DefaultObjectWrapper());

            // 指定模板文件
            Template template = configuration.getTemplate("vo.ftl");

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            root.put("vo",tableInfo);


            File outDirFile = new File("./src-template");
            if(!outDirFile.exists()){
                outDirFile.mkdir();
            }
            javaFile = toJavaFilename(outDirFile, "com.shuiyujie", tableInfo.getName());

            // 步骤四：合并 模板 和 数据模型
            // 创建.java类文件
            if(javaFile != null){
                Writer javaWriter = new FileWriter(javaFile);
                template.process(root, javaWriter);
                javaWriter.flush();
                System.out.println("文件生成路径：" + javaFile.getCanonicalPath());

                javaWriter.close();
            }
            // 输出到Console控制台
//            Writer out = new OutputStreamWriter(System.out);
//            OutputStream os = new FileOutputStream("C:/java/hello");

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/Users/shui/workspace/code/user.java"), "utf-8"));
            template.process(root, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return false;
    }



    /**
     * 创建.java文件所在路径 和 返回.java文件File对象
     * @param outDirFile 生成文件路径
     * @param javaPackage java包名
     * @param javaClassName java类名
     * @return
     */
    private static File toJavaFilename(File outDirFile, String javaPackage, String javaClassName) {
        String packageSubPath = javaPackage.replace('.', '/');
        File packagePath = new File(outDirFile, packageSubPath);
        File file = new File(packagePath, javaClassName + ".java");
        if(!packagePath.exists()){
            packagePath.mkdirs();
        }
        return file;
    }

    public static void main(String[] args) throws Exception {

        new VOTask().doInternale();
    }
}
