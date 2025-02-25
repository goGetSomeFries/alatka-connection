package com.alatka.connection.file.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.file.FileV3InboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.file.config.FileTailInboundChannelAdapterFactoryBean;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;

import java.io.File;

/**
 * {@link ApacheCommonsFileTailingMessageProducer}组件注册器
 *
 * @author ybliu
 * @see ApacheCommonsFileTailingMessageProducer
 */
public class FileV3InboundRegister extends InboundComponentRegister<FileV3InboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, FileV3InboundProperty property) {
        builder.addPropertyValue("file", new File(property.getFile()));
        if (property.getDelay() != null) {
            builder.addPropertyValue("delay", property.getDelay());
        }
        if (property.getEnd() != null) {
            builder.addPropertyValue("end", property.getEnd());
        }
        if (property.getReopen() != null) {
            builder.addPropertyValue("reopen", property.getReopen());
        }
        if (property.getTailAttemptsDelay() != null) {
            builder.addPropertyValue("tailAttemptsDelay", property.getTailAttemptsDelay());
        }
        if (property.getTaskExecutor() != null) {
            builder.addPropertyValue("taskExecutor", property.getTaskExecutor());
        }
    }

    @Override
    protected Class<FileTailInboundChannelAdapterFactoryBean> componentClass(FileV3InboundProperty property) {
        return FileTailInboundChannelAdapterFactoryBean.class;
    }

    @Override
    public Class<FileV3InboundProperty> mappingKey() {
        return FileV3InboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.file_v3.name();
    }

}
