package org.example.langchain4j.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

//初级智能体
@AiService(wiringMode = EXPLICIT,
        chatModel = "ollamaChatModel",
        chatMemory = "chatMemory"
)
public interface MemoryAssistant {

    String chat(String message);
}
