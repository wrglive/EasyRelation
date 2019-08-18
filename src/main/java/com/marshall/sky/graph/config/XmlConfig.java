package com.marshall.sky.graph.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : livE
 */
@Component
@ConfigurationProperties("sky.graphdb")
public class XmlConfig {

  List<String> prefixTableName;

}
