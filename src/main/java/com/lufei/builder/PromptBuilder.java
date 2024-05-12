package com.lufei.builder;

import com.lufei.dto.ChatAiDTO;

/**
 * 提示词构建器
 * @author kun.li
 */
public interface PromptBuilder {
    /**
     * 支持的提示词类型
     */
    boolean supportPromptType(String type);

    /**
     * 得到提示词
     */
    String buildPromptMessage(ChatAiDTO chatAiDTO);
}
