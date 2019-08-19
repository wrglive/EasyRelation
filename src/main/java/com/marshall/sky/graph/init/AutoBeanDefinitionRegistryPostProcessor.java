package com.marshall.sky.graph.init;

import com.alibaba.fastjson.JSONObject;
import com.marshall.sky.graph.dao.GraphDaoImpl;
import com.marshall.sky.graph.util.StringUtils3;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author : livE
 */
@Configuration
public class AutoBeanDefinitionRegistryPostProcessor implements
    BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    Properties properties = new Properties();
    try {
      InputStream inputStream = AutoBeanDefinitionRegistryPostProcessor.class.getClassLoader()
          .getResourceAsStream("sky-graphdb.properties");
      properties.load(inputStream);
    } catch (Exception e) {
      throw new RuntimeException("load properties error!");
    }
    String value = properties.getProperty("prefixTableName", "[]");
    List<String> tableName = JSONObject.parseArray(value, String.class);

    for (String s : tableName) {
      BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
          .genericBeanDefinition(GraphDaoImpl.class);
      beanDefinitionBuilder.addPropertyValue("tableName", s);
      BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
      registry.registerBeanDefinition(StringUtils3.underline2Camel(s, true) + "GraphDao",
          beanDefinition);
    }
/*
    //构造bean定义
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .genericBeanDefinition(GraphDaoImpl.class);
    beanDefinitionBuilder.addPropertyValue("tableName", "user_channel");
    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    registry.registerBeanDefinition("userChannelGraphDao", beanDefinition);
    //===========>
    //构造bean定义
    BeanDefinitionBuilder beanDefinitionBuilder2 = BeanDefinitionBuilder
        .genericBeanDefinition(GraphDaoImpl.class);

    beanDefinitionBuilder2.addPropertyValue("tableName", "theme_channel");
    BeanDefinition beanDefinition2 = beanDefinitionBuilder2.getBeanDefinition();
    registry.registerBeanDefinition("themeChannelGraphDao", beanDefinition2);*/

  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {
  }

}
