package com.socket.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager configManager;
    private static Properties properties;

    //读取配置文件
    private ConfigManager() {
        String configFile = "application.properties";
        properties = new Properties();
        //根据classpath获取配置文件当前类所在包的根目录下
        InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            //读取配置文件
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过懒汉模式设置实例化的个数
    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager();
        }
        return configManager;
    }

    //通过key获取对应的value
    public String getString(String key) {
        return properties.getProperty(key);
    }
}
