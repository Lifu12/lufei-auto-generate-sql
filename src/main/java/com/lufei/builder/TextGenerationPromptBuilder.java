package com.lufei.builder;

import com.lufei.dto.ChatAiDTO;
import org.springframework.stereotype.Component;

/**
 * 文本生成
 *
 * @author kun.li
 */
@Component
public class TextGenerationPromptBuilder implements PromptBuilder {
    @Override
    public boolean supportPromptType(String type) {
        return "TEXT_GENERATION".equals(type);
    }

    @Override
    public String buildPromptMessage(ChatAiDTO chatAiDTO) {
        return chatAiDTO.getContent();
    }
}
