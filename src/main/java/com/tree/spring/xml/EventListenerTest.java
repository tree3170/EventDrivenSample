package com.tree.spring.xml;

import com.tree.spring.enums.MealEnum;
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
 * EventListenerTest
 * <p>
 * CustomizePublisher
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
