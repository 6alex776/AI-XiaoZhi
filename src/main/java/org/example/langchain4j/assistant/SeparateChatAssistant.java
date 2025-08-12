package org.example.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "calculatorTools"
)
public interface SeparateChatAssistant {

    //@SystemMessage("你是我的女朋友，现在要回答我的问题")//系统提示词
    @SystemMessage(fromResource = "SystemMessages.txt")
    String chat(@MemoryId int id, @UserMessage String message);

    @SystemMessage(fromResource = "SystemMessages2.txt")
    String chat2(@MemoryId int id,
                 @UserMessage String message,
                 @V("username") String username,
                 @V("age")  int age
    );
}
