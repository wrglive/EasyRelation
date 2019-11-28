package com.marshall.sky.graph.init;

import com.marshall.sky.graph.config.YamlConfiguration;
import com.marshall.sky.graph.dao.GraphDaoImpl;
import com.marshall.sky.graph.model.MySQLBean;
import com.marshall.sky.graph.util.StringUtils3;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author : livE
 */
@Configuration
public class GraphBeanRegistryProcessor implements
    BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
        throws BeansException {
        Properties properties = YamlConfiguration.getProperties();
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        String driver = properties.getProperty("spring.datasource.driver-class-name");
        String url = properties.getProperty("spring.datasource.url");

        MySQLBean mySQLBean = MySQLBean.newInstance(username, password, driver, url);

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
            InitTableJDBCHandle initTableJDBCHandle = new InitTableJDBCHandle(createSQL, mySQLBean);
            initTableJDBCHandle.execute();
        }
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
        throws BeansException {
    }

}
