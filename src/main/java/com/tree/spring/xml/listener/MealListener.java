package com.tree.spring.xml.listener;

import com.tree.spring.xml.event.MealEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * package com.tree.spring.listener
 * description:
 *
 * @author tree
 * @date 2018-11-30 09:07
 */
@Component
public class MealListener implements ApplicationListener<MealEvent> {
    @Override
    public void onApplicationEvent(MealEvent event) {
        System.out.println(String.format(">>>>>>>>>>>thread:%s,type:%s,event:%s",
                Thread.currentThread().getName(), event.getMealEnum(), event));

        dispatchEvent(event);
    }

    private void dispatchEvent(MealEvent event) {
        switch (event.getMealEnum()) {
            case breakfast:
                System.out.println(event.getMealEnum() + " to handle!!!");
                break;
            case lunch:
                System.out.println(event.getMealEnum() + " to handle!!!");
                break;
            case dinner:
                System.out.println(event.getMealEnum() + " to handle!!!");
                break;
            default:
                System.out.println(event.getMealEnum() + " error!!!");
                break;
        }
    }
}

