package business.layer.utils;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "src/main/resources/config.txt";

    public ConfigFileReader() {
        BufferedReader reader;
        // Open the file
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the config.txt file.");
    }

/*    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the config.txt file.");
    }*/

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url is not specified in the config.txt file.");
    }

    public String getUrlHero() {
        String url1 = properties.getProperty("urlHero");
        if (url1 != null) return url1;
        else throw new RuntimeException("getUrlHero is not specified in the config.txt file.");
    }

    public String getEmail() {
        String email = properties.getProperty("email");
        if (email != null) return email;
        else throw new RuntimeException("email is not specified in the config.txt file.");
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if (password != null) return password;
        else throw new RuntimeException("password is not specified in the config.txt file.");
    }
}
