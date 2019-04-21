package chapter_01.knight.sia;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
    public static void main(String[] args) throws Exception{
        // 加载Spring上下文
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/chapter_01/knight.xml");
        // 获取 knight bean
        Knight knight = context.getBean(Knight.class);
        // 使用 knight
        knight.embarkOnQuest();

        context.close();
    }
}
