package com.lufei.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 提示词类型
 *
 * @author kun.li
 */
@Getter
@RequiredArgsConstructor
public enum PromptType {

    /**
     * 自然语言转换成SQL
     */
    NL_2_SQL("NL_2_SQL", "将自然语言转换成SQL查询"),
    /**
     * 解释SQL
     */
    SQL_EXPLAIN("SQL_EXPLAIN", "解释SQL"),
    /**
     * 校验SQL
     */
    SQL_CHECKED("SQL_CHECKED", "SQL语法检查"),
    /**
     * 文本对话
     */
    TEXT_GENERATION("TEXT_GENERATION","文本生成");


    private final String code;

    private final String describe;


    public static String getDescribe(String code) {
        for (PromptType promptType : values()) {
            if (promptType.getCode().equals(code)) {
                return promptType.getDescribe();
            }
        }
        return null;
    }


}
