package com.lufei.adapter;

import com.lufei.enums.ModelType;
import com.lufei.enums.OpenAIVersion;

/**
 * 模型工厂
 *
 * @author kun.li
 */
public interface ModelAdapter {
    /**
     * 支持的类型
     *
     * @param modelType 模型类型
     */
    boolean supportModelType(ModelType modelType);

    /**
     * 获取答案
     *
     * @param prompt 提示词
     */
    default String getAnswer(String prompt) {
        return getAnswer(prompt, OpenAIVersion.GPT35);
    }

    default String getAnswer(String prompt, OpenAIVersion modelVersion) {
        return null;
    }


}
