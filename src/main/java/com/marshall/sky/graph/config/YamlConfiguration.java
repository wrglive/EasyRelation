package com.marshall.sky.graph.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;


public class YamlConfiguration {
    private YamlConfiguration(){}

    public static final Properties getProperties() {

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("/application.yaml"));
        Properties properties = yaml.getObject();
        String fileName = properties.getProperty("spring.profiles.active[0]");
        if (StringUtils.isBlank(fileName)) {
            return properties;
        }
        String path = "application-" + fileName + ".yaml";
        YamlPropertiesFactoryBean newYaml = new YamlPropertiesFactoryBean();
        newYaml.setResources(new ClassPathResource("/" + path));
        return newYaml.getObject();
    }
}
