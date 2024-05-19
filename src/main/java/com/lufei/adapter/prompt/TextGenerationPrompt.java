package com.lufei.adapter.prompt;

import com.lufei.dto.ChatAiDTO;
import com.lufei.adapter.PromptAdapter;
import org.springframework.stereotype.Component;

/**
 * 文本生成
 *
 * @author kun.li
 */
@Component
public class TextGenerationPrompt implements PromptAdapter {
    @Override
    public boolean supportPromptType(String type) {
        return "TEXT_GENERATION".equals(type);
    }

    @Override
    public String buildPromptMessage(ChatAiDTO chatAiDTO) {
        return chatAiDTO.getContent();
    }
}
