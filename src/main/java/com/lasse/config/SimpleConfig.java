package com.lasse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Lance on 2018/6/30.
 */
@Configuration
@ImportResource("classpath:/elasticsearch-config.xml")
public class SimpleConfig {
}
