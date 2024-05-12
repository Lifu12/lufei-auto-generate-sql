package com.lufei.factory.prompt;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.lufei.dto.ChatAiDTO;
import com.lufei.enums.PromptType;
import com.lufei.factory.PromptFactory;
import com.lufei.properties.DbProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kun.li
 */
@Component
public class Nlp2SqlPrompt implements PromptFactory {

    @Autowired
    private DbProperties dbProperties;

    private final String CREATE_TABLE_META_DATA = "show create table %s ";

    @Override
    public boolean supportPromptType(String type) {
        return "NL_2_SQL".equals(type);
    }

    @Override
    public String buildPromptMessage(ChatAiDTO chatAiDTO) {
        String promptTemplate = "请根据以下table properties和SQL input%s.\n %s SQL tables, with their properties: \n ``` \n %s \n``` \n SQL input: %s \n - 不要生成任何解释或者讨论";
        String describe = PromptType.getDescribe(chatAiDTO.getPromptType());
        return String.format(promptTemplate, describe, dbProperties.getDbType(), buildTableDDL(chatAiDTO.getTableNames()), chatAiDTO.getContent());
    }

    public String buildTableDDL(List<String> tableNames) {
        SimpleDataSource simpleDataSource = new SimpleDataSource(
                dbProperties.getUrl(), dbProperties.getUsername(),
                dbProperties.getPassword(), dbProperties.getDriverClassName()
        );
        Db use = DbUtil.use(simpleDataSource);
        StringBuilder tableDDL = new StringBuilder();
        try {
            for (String tableName : tableNames) {
                Entity entity = use.queryOne(String.format(CREATE_TABLE_META_DATA, tableName));
                String metaData = entity.getStr("create table");
                tableDDL.append(metaData).append("\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tableDDL.toString();
    }

}
