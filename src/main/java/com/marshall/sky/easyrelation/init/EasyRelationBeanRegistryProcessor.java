package com.marshall.sky.easyrelation.init;

import com.marshall.sky.easyrelation.dao.EasyRelationDaoImpl;
import com.marshall.sky.easyrelation.dao.JdbcPoolFactory;
import com.marshall.sky.easyrelation.util.StringUtils3;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : livE
 */
@Configuration
public class EasyRelationBeanRegistryProcessor extends JdbcPoolFactory implements
    BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    String value = environment.getProperty("sky.easy-relation.prefixTableNames", "");
    String[] tableName = value.split(",");

    for (String s : tableName) {
      BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
          .genericBeanDefinition(EasyRelationDaoImpl.class);
      beanDefinitionBuilder.addPropertyValue("tableName", s);
      BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
      beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
      beanDefinition.setAutowireCandidate(true);
      registry.registerBeanDefinition(s, beanDefinition);
      String createSQL = CreateTableSqlBuilder.buildCreateSQL(s);
      this.createTable(createSQL);
    }
  }


  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {
  }

  private void createTable(String sql) {
    try (final Connection connection = getPool().getConnection();
        final Statement statement = connection.createStatement()) {
      statement.execute(sql);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
