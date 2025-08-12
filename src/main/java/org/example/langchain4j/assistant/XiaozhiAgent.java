package org.example.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "XiaozhiChatMemoryProvider",
        tools = "appointmentTools"
)
public interface XiaozhiAgent {

    @SystemMessage(fromResource = "Xiaozhi.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String message);

}
