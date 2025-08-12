package org.example.langchain4j;

import dev.langchain4j.data.message.ChatMessage;
import org.example.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoCrudTest {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
//    public void testInsert() {
//        mongoTemplate.insert(new ChatMessages(1L,"msg"));
//    }

    @Test
    public void testInsert2(){
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("消息");
        mongoTemplate.insert(chatMessages);
    }

    @Test
    public void testSelectById(){
        ChatMessages chatMessages = mongoTemplate.findById("6899a6b575908cdab7facdb1",ChatMessages.class);
        System.out.println(chatMessages);
    }

    @Test
    public void testUpdate(){
        Criteria criteria = Criteria.where("_id").is("100");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content","新消息");

        //upsert:如果没有对应id则会直接插入
        mongoTemplate.upsert(query,update,ChatMessages.class);
    }

    @Test
    public void testDelete(){
        Criteria criteria = Criteria.where("_id").is("1");
        Query query = new Query(criteria);
        mongoTemplate.remove(query,ChatMessages.class);
    }
}
