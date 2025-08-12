package org.example.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4jApplicationTests {

    /**
     * gpt-4o-mini语言模型接入测试
     */
    @Test
    public void testGPTDemo() {
        //初始化模型
        OpenAiChatModel model = OpenAiChatModel.builder()
                //LangChain4j提供的代理服务器，该代理服务器会将演示密钥替换成真实密钥， 再将请求转发给OpenAI API
                .baseUrl("http://langchain4j.dev/demo/openai/v1") //设置模型api地址（如果apiKey = "demo"，则可省略baseUrl的配置）
                .apiKey("demo") //设置模型apiKey
                .modelName("gpt-4o-mini") //设置模型名称 尚硅⾕
                .build();
        //向模型提问
        String answer = model.chat("你叫什么名字");
        //输出结果
        System.out.println(answer);
    }

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testOpenAiChat() {
        String chat = openAiChatModel.chat("你是什么版本的？");
        System.out.println(chat);
    }

    //测试ollama
    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Test
    public void testOllamaChat() {
        String chat = ollamaChatModel.chat("你是什么版本的？");
        System.out.println(chat);
    }

    /**
     * 通义千问大模型
     */
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testDashScopeQwen() {
        //向模型提问
        String answer = qwenChatModel.chat("你是什么版本的");
        //输出结果
        System.out.println(answer);
    }

    @Test
    public void testDashScopeWanx() {
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .modelName("wan2.2-t2i-plus")
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .build();
        Response<Image> response = wanxImageModel.generate("画面中央是一座垂直延伸的巨型摩天大楼，楼体布满流光溢彩的全息广告屏，广告中是悬浮的机械义体与霓虹文字，文字呈现中英文混合的赛博标语。大楼底部是潮湿的雨巷，青石板路上映着红蓝交错的霓虹倒影，雨水从空中的悬浮管道滴落，形成细密的水线。" +
                "巷口站着一位穿着黑色皮质风衣的女性，短发挑染着荧光蓝，脸上戴着半透明的智能面罩，面罩上跳动着数据流光斑。她的右手握着一把线条凌厉的能量武器，武器边缘散发着淡紫色光晕。背景中，多架无人机拖着霓虹光轨从楼宇间穿梭，远处的空中交通层悬浮着反重力飞行器，飞行器的引擎喷出淡粉色离子焰。" +
                "整体色调以深蓝、紫色为基底，霓虹红、电子蓝、荧光粉作为点缀色，画面需带有轻微的胶片颗粒感和镜头眩光效果，营造潮湿、迷离又充满科技张力的未来都市氛围，细节丰富到能看清建筑表面的机械纹理与雨水中的倒影层次。");
        System.out.println(response.content().url());
    }
}
