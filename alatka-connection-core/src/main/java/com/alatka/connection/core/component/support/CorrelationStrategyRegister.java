package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.CorrelationStrategyProperty;
import com.alatka.connection.core.support.CorrelationStrategyHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.integration.aggregator.ExpressionEvaluatingCorrelationStrategy;
import org.springframework.integration.aggregator.HeaderAttributeCorrelationStrategy;
import org.springframework.integration.aggregator.MethodInvokingCorrelationStrategy;

/**
 * {@link CorrelationStrategy}组件注册器
 *
 * @author ybliu
 * @see CorrelationStrategy
 */
public class CorrelationStrategyRegister extends MultiTypeSupportComponentRegister<CorrelationStrategyProperty> {

    @Override
    public Class<CorrelationStrategyProperty> mappingKey() {
        return CorrelationStrategyProperty.class;
    }

    @Override
    protected void initialize() {
        initMap();

        initComponentClass(CorrelationStrategyProperty.Type.header, HeaderAttributeCorrelationStrategy.class);
        initComponentInit(CorrelationStrategyProperty.Type.header, (builder, params) -> {
            CorrelationStrategyProperty.Header header = (CorrelationStrategyProperty.Header) params;
            builder.addConstructorArgValue(header.getKey());
        });

        initComponentClass(CorrelationStrategyProperty.Type.expression, ExpressionEvaluatingCorrelationStrategy.class);
        initComponentInit(CorrelationStrategyProperty.Type.expression, (builder, params) -> {
            CorrelationStrategyProperty.Expression expression = (CorrelationStrategyProperty.Expression) params;
            builder.addConstructorArgValue(expression.getExpression());
        });

        initComponentClass(CorrelationStrategyProperty.Type.custom, MethodInvokingCorrelationStrategy.class);
        initComponentInit(CorrelationStrategyProperty.Type.custom, (builder, params) -> {
            CorrelationStrategyProperty.Custom custom = (CorrelationStrategyProperty.Custom) params;
            Object instance = ClassUtil.newInstance(custom.getClassName());
            if (!(instance instanceof CorrelationStrategyHandler)) {
                throw new IllegalArgumentException(instance + " must be an instance of " + CorrelationStrategyHandler.class.getName());
            }
            builder.addConstructorArgValue(instance);
            builder.addConstructorArgValue(CorrelationStrategyHandler.METHOD_NAME);
        });
    }

}
