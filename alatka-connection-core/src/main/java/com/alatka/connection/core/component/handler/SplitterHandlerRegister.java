package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.splitter.MethodInvokingSplitter;

import java.util.function.Function;

/**
 * 将上游集合数据拆分为单个元素发送给下游，类似{@link java.util.stream.Stream#flatMap(Function)}
 *
 * @author ybliu
 */
public class SplitterHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        builder.addConstructorArgValue(new InnerSplitter())
                .addConstructorArgValue(InnerSplitter.METHOD_NAME);
    }

    @Override
    protected Class<MethodInvokingSplitter> componentClass() {
        return MethodInvokingSplitter.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.splitter;
    }

    public class InnerSplitter {

        private static final String METHOD_NAME = "split";

        public Object split(Object object) {
            return object;
        }
    }
}
