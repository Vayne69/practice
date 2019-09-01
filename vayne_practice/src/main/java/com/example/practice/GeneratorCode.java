package com.example.practice;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dell
 */
public class GeneratorCode {

    public static void main(String[] args) throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //如果这里出现空指针，直接写绝对路径即可。
        String genCfg = "/generatorConfig.xml";
        File configFile = new File(GeneratorCode.class.getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
