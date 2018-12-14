package com.tree.spring.xml.publisher;

import com.tree.spring.event.MealEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * package com.tree.spring.publisher
 * description:事件分发器
 *
 * @author tree
 * @date 2018-11-30 09:05
 */
@Component
public class CustomizePublisher  implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(MealEvent event) {
        //发布
        applicationEventPublisher.publishEvent(event);
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}


