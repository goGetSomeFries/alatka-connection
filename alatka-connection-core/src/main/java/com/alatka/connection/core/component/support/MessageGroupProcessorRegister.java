package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.MessageGroupProcessorProperty;
import com.alatka.connection.core.support.MessageGroupProcessorHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.integration.aggregator.*;

/**
 * {@link MessageGroupProcessor}组件注册器
 *
 * @author ybliu
 * @see MessageGroupProcessor
 */
public class MessageGroupProcessorRegister extends MultiTypeSupportComponentRegister<MessageGroupProcessorProperty> {

    @Override
    public Class<MessageGroupProcessorProperty> mappingKey() {
        return MessageGroupProcessorProperty.class;
    }

    @Override
    protected void initialize() {
        super.initialize();

        initComponentClass(MessageGroupProcessorProperty.Type.simple, SimpleMessageGroupProcessor.class);
        initComponentInit(MessageGroupProcessorProperty.Type.simple, (builder, params) -> {
        });

        initComponentClass(MessageGroupProcessorProperty.Type.fallback, DefaultAggregatingMessageGroupProcessor.class);
        initComponentInit(MessageGroupProcessorProperty.Type.fallback, (builder, params) -> {
        });

        initComponentClass(MessageGroupProcessorProperty.Type.expression, ExpressionEvaluatingMessageGroupProcessor.class);
        initComponentInit(MessageGroupProcessorProperty.Type.expression, (builder, params) -> {
            MessageGroupProcessorProperty.Expression expression = (MessageGroupProcessorProperty.Expression) params;
            builder.addConstructorArgValue(expression.getExpression());
        });

        initComponentClass(MessageGroupProcessorProperty.Type.custom, MethodInvokingMessageGroupProcessor.class);
        initComponentInit(MessageGroupProcessorProperty.Type.custom, (builder, params) -> {
            MessageGroupProcessorProperty.Custom custom = (MessageGroupProcessorProperty.Custom) params;
            Object instance = ClassUtil.newInstance(custom.getClassName());
            if (!(instance instanceof MessageGroupProcessorHandler)) {
                throw new IllegalArgumentException(instance + " must be an instance of " + MessageGroupProcessorHandler.class.getName());
            }
            builder.addConstructorArgValue(instance);
            builder.addConstructorArgValue(MessageGroupProcessorHandler.METHOD_NAME);
        });
    }

}
