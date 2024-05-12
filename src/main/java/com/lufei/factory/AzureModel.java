package com.lufei.factory;

import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.lufei.enums.ModelType;
import com.lufei.enums.OpenAIVersion;
import com.lufei.util.AzureAiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 微软Ai Model
 *
 * @author kun.li
 */
@Component
public class AzureModel implements ModelFactory {


    @Autowired
    private AzureAiRequest azureAiRequest;


    @Override
    public boolean supportModelType(ModelType modelType) {
        return ModelType.AZURE_AI.equals(modelType);
    }


    @Override
    public String getAnswer(String prompt, OpenAIVersion modelVersion) {
        String content = null;
        ArrayList<ChatRequestMessage> messages = new ArrayList<>();
        messages.add(new ChatRequestUserMessage(prompt));
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(messages);
        if (modelVersion == OpenAIVersion.GPT35) {
            content = azureAiRequest.getContent(chatCompletionsOptions);
        }
        return content;
    }
}
