package com.qa.gorest.configuration;

import com.qa.gorest.FrameworkException.APIFrameworkException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private Properties prop;
    private FileInputStream ip;

    public Properties initializeProp() {
        prop = new Properties();
        String enviName = System.getProperty("env");
        try {
            if (enviName == null) {
                System.out.println("Environment not Provided, hence running in default Env QA");
                ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

            } else {
                System.out.println("Runniung on " + enviName);

                switch (enviName.toLowerCase().trim()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;

                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;

                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;

                    default:
                        System.out.println("Give correct env name" + enviName);
                        throw new APIFrameworkException("WRONG ENVI GIVEN");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}


