package com.lufei.controller;

import com.lufei.builder.PromptBuilder;
import com.lufei.dto.ChatAiDTO;
import com.lufei.enums.ModelType;
import com.lufei.enums.OpenAIVersion;
import com.lufei.factory.AzureModel;
import com.lufei.factory.ModelFactory;
import com.lufei.util.SpringApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ai 对话
 *
 * @author kun.li
 */
@RestController
@RequestMapping("chat")
@Slf4j
public class ChatAiController {


    @Autowired
    private List<PromptBuilder> promptBuilderList;

    @Autowired
    private List<ModelFactory> modelFactoryList;


    @PostMapping("ai")
    public String chatAi(@RequestBody ChatAiDTO chatAiDTO) {
        String prompt = buildPrompt(chatAiDTO);
        log.info("prompt:{}", prompt);
        return getAnswer(chatAiDTO, prompt);
    }

    public String getAnswer(ChatAiDTO chatAiDTO, String prompt) {
        // model 版本
        OpenAIVersion modelVersion = OpenAIVersion.getModelVersion(chatAiDTO.getModelVersion());
        // model 类型
        ModelType modelType = ModelType.getModelType(chatAiDTO.getModel());
        // 得到模型工厂
        ModelFactory modelFactory = modelFactory(modelType);
        // 得到答案
        return modelFactory.getAnswer(prompt, modelVersion);
    }

    /**
     * 模型适配器
     */
    public ModelFactory modelFactory(ModelType modelType) {
        for (ModelFactory modelFactory : modelFactoryList) {
            if (modelFactory.supportModelType(modelType)) {
                return modelFactory;
            }
        }
        return SpringApplicationUtil.getBean(AzureModel.class);
    }

    /**
     * 提示词适配构建器
     */
    public String buildPrompt(ChatAiDTO chatAiDTO) {
        for (PromptBuilder promptBuilder : promptBuilderList) {
            if (promptBuilder.supportPromptType(chatAiDTO.getPromptType())) {
                return promptBuilder.buildPromptMessage(chatAiDTO);
            }
        }
        return "暂无法回答";
    }
}
