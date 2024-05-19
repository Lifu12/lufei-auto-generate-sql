package com.lufei.controller;

import com.lufei.adapter.PromptAdapter;
import com.lufei.dto.ChatAiDTO;
import com.lufei.enums.ModelType;
import com.lufei.enums.OpenAIVersion;
import com.lufei.adapter.model.AzureModel;
import com.lufei.adapter.ModelAdapter;
import com.lufei.util.ResponseResult;
import com.lufei.util.SpringApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private List<PromptAdapter> promptAdapterList;

    @Autowired
    private List<ModelAdapter> modelAdapterList;

    /**
     * ai统一访问入口非流
     */
    @PostMapping("ai")
    public ResponseResult chatAi(@RequestBody ChatAiDTO chatAiDTO) {
        String prompt = buildPrompt(chatAiDTO);
        log.info("prompt:{}", prompt);
        return ResponseResult.success(getAnswer(chatAiDTO, prompt));
    }

    public String getAnswer(ChatAiDTO chatAiDTO, String prompt) {
        // model 版本
        OpenAIVersion modelVersion = OpenAIVersion.getModelVersion(chatAiDTO.getModelVersion());
        // model 类型
        ModelType modelType = ModelType.getModelType(chatAiDTO.getModel());
        // 得到模型工厂
        ModelAdapter modelAdapter = modelFactory(modelType);
        // 得到答案
        return modelAdapter.getAnswer(prompt, modelVersion);
    }

    /**
     * 模型适配器
     */
    public ModelAdapter modelFactory(ModelType modelType) {
        for (ModelAdapter modelAdapter : modelAdapterList) {
            if (modelAdapter.supportModelType(modelType)) {
                return modelAdapter;
            }
        }
        return SpringApplicationUtil.getBean(AzureModel.class);
    }

    /**
     * 提示词适配
     */
    public String buildPrompt(ChatAiDTO chatAiDTO) {
        for (PromptAdapter promptAdapter : promptAdapterList) {
            if (promptAdapter.supportPromptType(chatAiDTO.getPromptType())) {
                return promptAdapter.buildPromptMessage(chatAiDTO);
            }
        }
        return "暂无法回答";
    }
}
