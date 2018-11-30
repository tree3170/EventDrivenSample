package com.tree.spring.xml;

import com.tree.spring.xml.enums.MealEnum;
import com.tree.spring.xml.event.MealEvent;
import com.tree.spring.xml.publisher.CustomizePublisher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * package com.tree.spring
 * description:
 * MealListener ：MealEvent                  演员
 * <p>
 * TroubleListener ：TroubleEvent         演员
 * <p>
 * AllAcceptedListener                            演员
 * <p>
 * MealEnum                                          道具
 * <p>
 * TestPortal                                           入口
 * <p>
 * CustomizePublisher                           导演
 *
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
            System.out.println("bean----:" + definitionName);
        }
        System.err.println();
        CustomizePublisher customizePublisher = applicationContext.getBean(CustomizePublisher.class);


        Thread thread = new Thread(()-> {
                try {
                    System.out.println("开始吃饭：");

                    MealEvent lunchEvent = new MealEvent("A吃午饭了", MealEnum.lunch);
                    MealEvent breakfastEvent = new MealEvent("B吃早饭了", MealEnum.breakfast);
                    MealEvent dinnerEvent = new MealEvent("C吃晚饭了", MealEnum.dinner);
                    customizePublisher.publish(lunchEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(breakfastEvent);
                    TimeUnit.SECONDS.sleep(1l);
                    customizePublisher.publish(dinnerEvent);
                    TimeUnit.SECONDS.sleep(1l);

                    System.out.println("他们吃完了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"meal-thread");
        thread.start();

        System.out.println(Thread.currentThread().getName() + " is waiting for ....");
        thread.join();
        System.out.println("Done!!!!!!!!!!!!");
    }
}
