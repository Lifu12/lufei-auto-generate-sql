package com.lufei.builder;

import com.lufei.dto.ChatAiDTO;
import com.lufei.enums.PromptType;
import org.springframework.stereotype.Component;

/**
 * 检查sql的正确性
 * @author kun.li
 */
@Component
public class SQLCheckedPromptBuilder implements PromptBuilder {
    @Override
    public boolean supportPromptType(String type) {
        return "SQL_CHECKED".equals(type);
    }

    @Override
    public String buildPromptMessage(ChatAiDTO chatAiDTO) {
        String promptTemplate = " 请根据以下SQL input%s. \n\n SQL input: %s \n\n - 请直接给出修改后的SQL语句 \n - 不要有任何解释或者讨论的话语";
        String describe = PromptType.getDescribe(chatAiDTO.getPromptType());
        return String.format(promptTemplate,describe,chatAiDTO.getContent());
    }
}
