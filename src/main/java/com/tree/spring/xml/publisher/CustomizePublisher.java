package com.tree.spring.xml.publisher;

import com.tree.spring.xml.event.MealEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * package com.tree.spring.publisher
 * description:导演负责分发事件
 *
 * @author tree
 * @date 2018-11-30 09:05
 */
@Component
public class CustomizePublisher  implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(MealEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}


