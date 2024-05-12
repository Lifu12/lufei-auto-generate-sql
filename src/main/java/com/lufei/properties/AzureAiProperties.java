package com.lufei.properties;

import lombok.Data;

/**
 * @author kun.li
 */
@Data
public class AzureAiProperties {
    /**
     * 地址
     */
    private String endpoint;

    /**
     * 密钥
     */
    private String openKey;
    /**
     * 命名空间 gpt3.5
     */
    private String nameSpace;
    /**
     * gpt4.0
     */
    private String nameSpaceLast;
    /**
     * 模型
     */
    private String model;
}
