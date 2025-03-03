package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.ReleaseStrategyProperty;
import com.alatka.connection.core.support.ReleaseStrategyHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.integration.aggregator.ExpressionEvaluatingReleaseStrategy;
import org.springframework.integration.aggregator.MessageCountReleaseStrategy;
import org.springframework.integration.aggregator.MethodInvokingReleaseStrategy;
import org.springframework.integration.aggregator.ReleaseStrategy;

/**
 * {@link ReleaseStrategy}组件注册器
 *
 * @author ybliu
 * @see ReleaseStrategy
 */
public class ReleaseStrategyRegister extends MultiTypeSupportComponentRegister<ReleaseStrategyProperty> {

    @Override
    public Class<ReleaseStrategyProperty> mappingKey() {
        return ReleaseStrategyProperty.class;
    }

    @Override
    protected void initialize() {
        super.initialize();

        initComponentClass(ReleaseStrategyProperty.Type.messageCount, MessageCountReleaseStrategy.class);
        initComponentInit(ReleaseStrategyProperty.Type.messageCount, (builder, params) -> {
            ReleaseStrategyProperty.MessageCount messageCount = (ReleaseStrategyProperty.MessageCount) params;
            builder.addConstructorArgValue(messageCount.getThreshold());
        });

        initComponentClass(ReleaseStrategyProperty.Type.expression, ExpressionEvaluatingReleaseStrategy.class);
        initComponentInit(ReleaseStrategyProperty.Type.expression, (builder, params) -> {
            ReleaseStrategyProperty.Expression expression = (ReleaseStrategyProperty.Expression) params;
            builder.addConstructorArgValue(expression.getExpression());
        });

        initComponentClass(ReleaseStrategyProperty.Type.custom, MethodInvokingReleaseStrategy.class);
        initComponentInit(ReleaseStrategyProperty.Type.custom, (builder, params) -> {
            ReleaseStrategyProperty.Custom custom = (ReleaseStrategyProperty.Custom) params;
            Object instance = ClassUtil.newInstance(custom.getClassName());
            if (!(instance instanceof ReleaseStrategyHandler)) {
                throw new IllegalArgumentException(instance + " must be an instance of " + ReleaseStrategyHandler.class.getName());
            }
            builder.addConstructorArgValue(instance);
            builder.addConstructorArgValue(ReleaseStrategyHandler.METHOD_NAME);
        });
    }

}
