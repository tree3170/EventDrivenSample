# Spring事件传播机制

具体参考下面图：<p>
ApplicationEvent.uml<p>
ApplicationListener.uml<p>
spring事件驱动模型.jpg<p>
事件发布机制流程图.png
```
1、ApplicationEventPublisher是Spring的事件发布接口，事件源通过该接口的pulishEvent方法发布事件。

2、ApplicationEventMulticaster就是Spring事件机制中的事件广播器，它默认提供一个SimpleApplicationEventMulticaster实现，如果用户没有自定义广播器，则使用默认的。它通过父类AbstractApplicationEventMulticaster的getApplicationListeners方法从事件注册表中获取事件监听器，并且通过invokeListener方法执行监听器的具体逻辑

3、ApplicationListener就是Spring的事件监听器接口，所有的监听器都实现该接口，本图中列出了典型的几个子类。其中RestartApplicationListnener在SpringBoot的启动框架中就有使用下载

4、在Spring中通常是ApplicationContext本身担任监听器注册表的角色，在其子类AbstractApplicationContext中就聚合了事件广播器ApplicationEventMulticaster和事件监听器ApplicationListnener，并且提供注册监听器的addApplicationListnener方法。

通过上图就能较清晰的知道当一个事件源产生事件时，它通过事件发布器ApplicationEventPublisher发布事件，然后事件广播器ApplicationEventMulticaster会去事件注册表ApplicationContext中找到事件监听器ApplicationListnener，并且逐个执行监听器的onApplicationEvent方法，从而完成事件监听器的逻辑。

```

## 参考：
### [Spring3.2.6中事件驱动模型实现原理深入源码分析](https://blog.csdn.net/u011066648/article/details/50997222)
### [spring事件通知机制详解](https://www.cnblogs.com/zhangxiaoguang/p/spring-notification.html)
### [Spring架构揭秘－事件监听机制](http://blog.51cto.com/12035884/1846043)