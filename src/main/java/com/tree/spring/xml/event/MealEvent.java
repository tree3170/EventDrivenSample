package com.tree.spring.xml.event;

import com.tree.spring.enums.MealEnum;
import org.springframework.context.ApplicationEvent;

/**
 * package com.tree.spring.event
 * description:吃饭事件
 *
 * @author tree
 * @date 2018-11-30 09:08
 */
public class MealEvent extends ApplicationEvent {

    private MealEnum mealEnum;

    /**
     * @param mealContent
     *        吃什么
     * @param mealEnum
     *        早餐还是午餐？
     */
    public MealEvent(String mealContent, MealEnum mealEnum) {
        super(mealContent);
        this.mealEnum = mealEnum;
    }

    public MealEnum getMealEnum() {
        return mealEnum;
    }
}

