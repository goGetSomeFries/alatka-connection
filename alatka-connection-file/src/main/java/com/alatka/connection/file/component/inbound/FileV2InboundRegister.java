package com.alatka.connection.file.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.file.FileV2InboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.file.config.FileTailInboundChannelAdapterFactoryBean;
import org.springframework.integration.file.tail.OSDelegatingFileTailingMessageProducer;

import java.io.File;

/**
 * {@link OSDelegatingFileTailingMessageProducer}组件注册器
 *
 * @author ybliu
 * @see OSDelegatingFileTailingMessageProducer
 */
public class FileV2InboundRegister extends InboundComponentRegister<FileV2InboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, FileV2InboundProperty property) {
        builder.addPropertyValue("file", new File(property.getFile()));
        builder.addPropertyValue("enableStatusReader", property.isEnableStatusReader());

        if (property.getTailAttemptsDelay() != null) {
            builder.addPropertyValue("tailAttemptsDelay", property.getTailAttemptsDelay());
        }
        if (property.getTaskExecutor() != null) {
            builder.addPropertyValue("taskExecutor", property.getTaskExecutor());
        }
    }

    @Override
    protected Class<FileTailInboundChannelAdapterFactoryBean> componentClass(FileV2InboundProperty property) {
        return FileTailInboundChannelAdapterFactoryBean.class;
    }

    @Override
    public Class<FileV2InboundProperty> mappingKey() {
        return FileV2InboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.file_v2.name();
    }

}
