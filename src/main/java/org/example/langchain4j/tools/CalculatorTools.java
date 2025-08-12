//Function Calling
package org.example.langchain4j.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTools {

    @Tool(name = "加法运算",value = "两个参数相加")//详细声明
    double sum(
            @ToolMemoryId int memoryId,//从实际方法中获取memoryId
            @P(value = "加数1",required = true)double a,//详细参数说明
            double b
    ) {
        System.out.println("调用加法运算 memoryId" + memoryId);
        return a + b;
    }
    @Tool
    double squareRoot(double x) {
        System.out.println("调用平方根运算");
        return Math.sqrt(x);
    }

}
