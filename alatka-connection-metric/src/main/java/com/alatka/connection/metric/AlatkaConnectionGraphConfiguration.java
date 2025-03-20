package com.alatka.connection.metric;

import com.alatka.connection.metric.model.InboundModel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashMap;
import java.util.Map;

public class AlatkaConnectionGraphConfiguration implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    private final Map<String, Object> map = new HashMap<>();

    private void buildGraph() {

    }

    private InboundModel buildInbound() {

        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().equals(this.applicationContext)) {
            buildGraph();
        }
    }
}
