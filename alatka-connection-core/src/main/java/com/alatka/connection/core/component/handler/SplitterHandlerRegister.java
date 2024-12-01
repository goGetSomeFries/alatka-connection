package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.SplitterMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.SplitterFactoryBean;

import java.util.Map;
import java.util.function.Function;

/**
 * 将上游集合数据拆分为单个元素发送给下游，类似{@link java.util.stream.Stream#flatMap(Function)}
 *
 * @author ybliu
 */
public class SplitterHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        super.preDoRegister(builder, property);
        Map<String, Object> params = property.getParams();
        params.put(KEY_CLASS_NAME, SplitterMessageHandler.class.getName());
    }

    @Override
    protected String handlerMethodName() {
        return SplitterMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<SplitterFactoryBean> componentClass() {
        return SplitterFactoryBean.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.splitter;
    }

}
