package com.lufei.util;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.OpenAIServiceVersion;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.KeyCredential;
import com.lufei.enums.OpenAIVersion;
import com.lufei.properties.AzureAiProperties;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author kun.li
 */
@RequiredArgsConstructor
public class AzureAiRequest {


    private final AzureAiProperties azureAiProperties;

    private OpenAIClient openAIClient;


    @PostConstruct
    public void init() {
        openAIClient = new OpenAIClientBuilder()
                .serviceVersion(OpenAIServiceVersion.V2023_12_01_PREVIEW)
                .endpoint(azureAiProperties.getEndpoint())
                .credential(new KeyCredential(azureAiProperties.getOpenKey())).buildClient();

    }

    public String getContent(ChatCompletionsOptions chatCompletionsOptions) {
        return getResponseMessage(chatCompletionsOptions, OpenAIVersion.GPT35).getContent();
    }

    public String getContent(ChatCompletionsOptions chatCompletionsOptions, OpenAIVersion openAIVersion) {
        return getResponseMessage(chatCompletionsOptions, openAIVersion).getContent();
    }

    public ChatResponseMessage getResponseMessage(ChatCompletionsOptions chatCompletionsOptions, OpenAIVersion openAIVersion) {
        List<ChatChoice> choiceList = getChoicesMessage(chatCompletionsOptions, openAIVersion);
        return choiceList.get(0).getMessage();
    }

    public List<ChatChoice> getChoicesMessage(ChatCompletionsOptions chatCompletionsOptions) {
        ChatCompletions chatCompletions = getChatCompletions(chatCompletionsOptions, OpenAIVersion.GPT4);
        return chatCompletions.getChoices();
    }

    public List<ChatChoice> getChoicesMessage(ChatCompletionsOptions chatCompletionsOptions, OpenAIVersion openAIVersion) {
        ChatCompletions chatCompletions = getChatCompletions(chatCompletionsOptions, openAIVersion);
        return chatCompletions.getChoices();
    }

    /**
     * 获取聊天完成
     */
    public ChatCompletions getChatCompletions(ChatCompletionsOptions chatCompletionsOptions, OpenAIVersion openAIVersion) {
        if (openAIVersion.getModelVersion().equals(OpenAIVersion.GPT35.getModelVersion())) {
            return openAIClient.getChatCompletions(azureAiProperties.getNameSpace(), chatCompletionsOptions);
        }
        return openAIClient.getChatCompletions(azureAiProperties.getNameSpaceLast(), chatCompletionsOptions);

    }
}
