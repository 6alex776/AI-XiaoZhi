package org.example.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.example.langchain4j.assistant.Assistant;
import org.example.langchain4j.assistant.MemoryAssistant;
import org.example.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat() {
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String chat = assistant.chat("介绍自己");
        System.out.println(chat);
    }

    //@AiService注解创建
    @Autowired
    private Assistant assistant;

    @Test
    public void testChat2() {
        String chat = assistant.chat("java反射机制");
        System.out.println(chat);
    }

    //聊天记忆
    @Test
    public void testChatMemory() {

        MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant1 = AiServices
                .builder(Assistant.class)
                .chatModel(qwenChatModel)
                .chatMemory(messageWindowChatMemory)
                .build();

        String chat1 = assistant1.chat("我叫linus");
        System.out.println(chat1);
        String chat2 = assistant1.chat("我是谁？");
        System.out.println(chat2);
    }

    //封装到配置类和接口
    @Autowired
    private MemoryAssistant memoryAssistant;
    @Test
    public void testChatMemory2() {

        String chat1 = memoryAssistant.chat("我叫linus");
        System.out.println(chat1);
        String chat2 = memoryAssistant.chat("我是谁？");
        System.out.println(chat2);
    }

    //隔离记忆，并保存回答到MongoDB
    @Autowired
    private SeparateChatAssistant  separateChatAssistant;
    @Test
    public void testChatMemory3() {

        String chat1 = separateChatAssistant.chat(1,"我叫linus");
        System.out.println(chat1);
        String chat2 = separateChatAssistant.chat(1,"我是谁？");
        System.out.println(chat2);

        String chat3 = separateChatAssistant.chat(2,"我是谁？");
        System.out.println(chat3);
    }
}
