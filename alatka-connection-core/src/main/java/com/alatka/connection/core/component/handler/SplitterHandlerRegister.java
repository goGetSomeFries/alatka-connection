package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.SplitterHandlerProperty;
import com.alatka.connection.core.support.SplitterMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.SplitterFactoryBean;

import java.util.function.Function;

/**
 * 将上游集合数据拆分为单个元素发送给下游，类似{@link java.util.stream.Stream#flatMap(Function)}
 *
 * @author ybliu
 */
public class SplitterHandlerRegister extends MessageProcessorRegister<SplitterHandlerProperty> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, SplitterHandlerProperty property) {
        super.preDoRegister(builder, property);
        property.setClassName(SplitterFactoryBean.class.getName());
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
    public Class<SplitterHandlerProperty> mappingKey() {
        return SplitterHandlerProperty.class;
    }

}
