package com.marshall.sky.graph.init;

import com.marshall.sky.graph.dao.GraphDaoImpl;
import com.marshall.sky.graph.model.MySQLBean;
import com.marshall.sky.graph.util.StringUtils3;
import java.io.InputStream;
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
public class GraphBeanRegistryProcessor implements
    BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    Properties properties = new Properties();
    try {
      InputStream inputStream = GraphBeanRegistryProcessor.class.getClassLoader()
          .getResourceAsStream("sky-graphdb.properties");
      properties.load(inputStream);
    } catch (Exception e) {
      throw new RuntimeException("load sky-graphdb.properties error!");
    }
    MySQLBean mySQLBean = MySQLBean
        .newInstance(properties.getProperty("username"), properties.getProperty("password"),
            properties.getProperty("jdbcDriver"), properties.getProperty("jdbcUrl"));

    String value = properties.getProperty("prefixTableName", ",");
    String[] tableName = value.split(",");

    for (String s : tableName) {
      BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
          .genericBeanDefinition(GraphDaoImpl.class);
      beanDefinitionBuilder.addPropertyValue("tableName", s);
      BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
      registry.registerBeanDefinition(StringUtils3.underline2Camel(s, true) + "GraphDao",
          beanDefinition);
      String createSQL = InitHandle.buildCreateSQL(s);
      InitTableJDBCHandle initTableJDBCHandle = new InitTableJDBCHandle(createSQL, mySQLBean);
      initTableJDBCHandle.execute();
    }
  }


  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {
  }

}
