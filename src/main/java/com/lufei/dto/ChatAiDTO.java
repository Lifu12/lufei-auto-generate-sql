package com.lufei.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kun.li
 */
@Data
public class ChatAiDTO implements Serializable {
    /**
     * 输入内容
     */
    private String content;
    /**
     * 提示词类型
     *
     * @see com.lufei.enums.PromptType
     */
    private String promptType;

    /**
     * 表名列表
     */
    private List<String> tableNames;

    /**
     * 模型
     * openai AzureAi
     */
    private String model;
    /**
     * 模型版本
     */
    private String modelVersion;
}
