package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.core.CustomOutboundProperty;
import com.alatka.connection.core.support.CustomHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class CustomOutboundRegister extends OutboundComponentRegister<CustomOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, CustomOutboundProperty property) {
        String beanName = property.getBeanName();
        String className = property.getClassName();
        if (beanName == null && className == null) {
            throw new IllegalArgumentException("BeanName and className must not be null both");
        }

        Class<?> clazz = ClassUtil.forName(beanName != null ? getBeanFactory().getBeanDefinition(beanName).getBeanClassName() : className);
        if (!CustomHandler.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz.getName() + " must be an instance of " + CustomHandler.class.getName());
        }

        if (beanName != null) {
            builder.addConstructorArgReference(beanName);
        } else {
            builder.addConstructorArgValue(ClassUtil.newInstance(className));
        }
        builder.addConstructorArgValue(CustomHandler.METHOD_NAME);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public Class<CustomOutboundProperty> mappingKey() {
        return CustomOutboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return OutboundModel.custom.name();
    }
}
