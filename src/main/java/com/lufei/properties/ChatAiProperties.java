package com.lufei.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * chat ai 配置属性类
 *
 * @author kun.li
 */
@Data
@ConfigurationProperties(ChatAiProperties.CHAT_AI_PRE)
public class ChatAiProperties {
    public final static String CHAT_AI_PRE = "chat.ai";

    /**
     * openai属性配置类
     */
    private OpenAiProperties openAi;
    /**
     * 微软ai属性配置类
     */
    private AzureAiProperties azureAi;


}
