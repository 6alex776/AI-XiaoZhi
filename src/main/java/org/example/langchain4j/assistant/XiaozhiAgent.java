package org.example.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "XiaozhiChatMemoryProvider",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXiaozhiPincone" //配置向量存储
)
public interface XiaozhiAgent {

    @SystemMessage(fromResource = "Xiaozhi.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String message);

}
