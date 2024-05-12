package com.lufei.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author kun.li
 */
@Getter
@RequiredArgsConstructor
public enum ModelType {
    /**
     * 微软ai
     */
    AZURE_AI("AzureAi");

    private final String code;

    public static ModelType getModelType(String code) {
        for (ModelType modelType : values()) {
            if (modelType.getCode().equals(code)) {
                return modelType;
            }
        }
        return ModelType.AZURE_AI;
    }

}
