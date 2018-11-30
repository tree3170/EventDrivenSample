package com.tree.spring.xml;

import com.tree.spring.enums.MealEnum;
import com.tree.spring.xml.event.MealEvent;
import com.tree.spring.xml.publisher.CustomizePublisher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * package com.tree.spring
 * description:
 * MealListener ：MealEvent
 * MealEnum
 * CustomizePublisher
 * EventListenerTest
 *
 * 事件（Event）： 用户点击按钮（Button），就会产生事件（Event）。这里就是点击事件（ClickEvent）。
 * 事件源（EventSource）：发生事件的根源（场所）就是事件源。这里按钮组件就是事件源。
 * 事件监听器（EventListener）：监听特定的事件并处理的程序。这里监听器就是点击事件的处理者。
 * 事件分发者（EventDispatcher）：将发生的事件通知相对应的事件处理者的程序。这里就是通知监听器处理事件的程序。
 *
 * 参考：
 * Spring3.2.6中事件驱动模型实现原理深入源码分析 ： https://blog.csdn.net/u011066648/article/details/50997222
 * spring事件通知机制详解 ：https://www.cnblogs.com/zhangxiaoguang/p/spring-notification.html
 * @author tree
 * @date 2018-11-30 09:32
 */
public class EventListenerTest {

    public static void main(String[] args) throws InterruptedException {
        final ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-mvc.xml");

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        System.err.println("==============打印definitionName=====================");
        for (String definitionName : definitionNames) {
            System.out.println("definitionName----:" + definitionName);
        }
        System.err.println();
        CustomizePublisher customizePublisher = applicationContext.getBean(CustomizePublisher.class);

        Thread thread = new Thread(()-> {
                try {
                    System.err.println("开始吃饭：");

                    MealEvent lunchEvent = new MealEvent("A吃午饭了", MealEnum.lunch);
                    MealEvent breakfastEvent = new MealEvent("B吃早饭了", MealEnum.breakfast);
                    MealEvent dinnerEvent = new MealEvent("C吃晚饭了", MealEnum.dinner);
                    customizePublisher.publish(lunchEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(breakfastEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(dinnerEvent);
                    TimeUnit.SECONDS.sleep(1l);

                    System.err.println("他们吃完了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"meal-thread");
        thread.start();

        System.err.println(Thread.currentThread().getName() + " 线程等待当前线程（meal-thread）执行完成....");
        thread.join();
        System.err.println("主线程与线程meal-thread执行完， 结束!!!!!!!!!!!!");
    }
}
