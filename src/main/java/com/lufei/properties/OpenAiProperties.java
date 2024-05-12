package com.lufei.properties;

import lombok.Data;

/**
 * @author kun.li
 */
@Data
public class OpenAiProperties {

    /**
     * 密钥
     */
    private String openKey;
    /**
     * 模型名称
     */
    private String modelName;
}
