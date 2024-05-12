package com.lufei.config;

import com.lufei.util.AzureAiRequest;
import com.lufei.properties.AzureAiProperties;
import com.lufei.properties.ChatAiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ai配置类
 * @author kun.li
 */
@Configuration
@EnableConfigurationProperties(ChatAiProperties.class)
public class AiConfig {

    /**
     * 微软AI
     */
    @Bean
    public AzureAiRequest azureAiRequest(ChatAiProperties chatAiProperties) {
        AzureAiProperties azureAi = chatAiProperties.getAzureAi();
        return new AzureAiRequest(azureAi);
    }
}
