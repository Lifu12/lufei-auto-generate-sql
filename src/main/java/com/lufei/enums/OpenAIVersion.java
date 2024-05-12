package com.lufei.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpenAIVersion {
    GPT35("gpt-35-turbo", "3.5"),
    GPT4("gpt-4", "4.0");
    private final String modelName;

    private final String modelVersion;

    public static OpenAIVersion getModelVersion(String modelName) {
        for (OpenAIVersion aiVersion : values()) {
            if (aiVersion.getModelName().equals(modelName)) {
                return aiVersion;
            }
        }
        return OpenAIVersion.GPT35;
    }

}