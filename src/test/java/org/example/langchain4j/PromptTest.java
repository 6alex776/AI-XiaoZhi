package org.example.langchain4j;

import org.example.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testSystemMessage() {
        String chat = separateChatAssistant.chat(4, "今晚什么安排呢？");
        System.out.println(chat);
    }

    @Test
    public void testUserInfo() {
        String chat = separateChatAssistant.chat2(5,"你的数据更新到什么时间呢","linus",21);
        System.out.println(chat);
    }
}
