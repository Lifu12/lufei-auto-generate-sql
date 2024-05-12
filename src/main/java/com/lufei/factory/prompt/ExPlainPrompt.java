package com.lufei.factory.prompt;

import com.lufei.dto.ChatAiDTO;
import com.lufei.enums.PromptType;
import com.lufei.factory.PromptFactory;
import org.springframework.stereotype.Component;

/**
 * 解释Sql
 *
 * @author kun.li
 */
@Component
public class ExPlainPrompt implements PromptFactory {
    @Override
    public boolean supportPromptType(String type) {
        return "SQL_EXPLAIN".equals(type);
    }

    @Override
    public String buildPromptMessage(ChatAiDTO chatAiDTO) {
        String promptTemplate = " 请根据以下SQL input%s. \n\n SQL input: %s \n\n  - 不要生成与本问题无关的回答";
        String describe = PromptType.getDescribe(chatAiDTO.getPromptType());
        return String.format(promptTemplate,describe,chatAiDTO.getContent());
    }
}
