package com.marshall.sky.graph.init;

import com.marshall.sky.graph.config.YamlConfiguration;
import com.marshall.sky.graph.dao.GraphDaoImpl;
import com.marshall.sky.graph.dao.mapper.SqlPoolFactory;
import com.marshall.sky.graph.util.StringUtils3;
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
import java.util.Properties;

/**
 * @author : livE
 */
@Configuration
public class GraphBeanRegistryProcessor extends SqlPoolFactory implements
    BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
        throws BeansException {
        Properties properties = YamlConfiguration.getProperties();
        String value = properties.getProperty("sky.graph.prefixTableNames", "");
        String[] tableName = value.split(",");

        for (String s : tableName) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(GraphDaoImpl.class);
            beanDefinitionBuilder.addPropertyValue("tableName", s);
            BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            //FIXME: 需要测试单例模式会不会对bean多态造成影响.
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            beanDefinition.setAutowireCandidate(true);
            registry.registerBeanDefinition(StringUtils3.underline2Camel(s, true) + "GraphDao",
                beanDefinition);
            String createSQL = InitHandle.buildCreateSQL(s);
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
