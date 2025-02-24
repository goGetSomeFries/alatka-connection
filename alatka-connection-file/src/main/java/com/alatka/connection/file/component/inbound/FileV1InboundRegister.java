package com.alatka.connection.file.component.inbound;

import com.alatka.connection.core.component.inbound.SourcePollingInboundRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.file.FileV1InboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;

/**
 * // TODO 未实现
 *
 * @author ybliu
 */
public class FileV1InboundRegister extends SourcePollingInboundRegister<FileV1InboundProperty> {

    @Override
    protected void doRegisterMessageSource(BeanDefinitionBuilder builder, FileV1InboundProperty property) {
        builder.addPropertyValue("scanEachPoll", property.isScanEachPoll());
        builder.addPropertyValue("autoCreateDirectory", property.isAutoCreateDirectory());
        builder.addPropertyValue("directory", new File(property.getDirectory()));
//        builder.addPropertyValue("filter", new FileSystemMarkerFilePresentFileListFilter());
    }

    @Override
    protected Class<FileReadingMessageSource> messageSourceClass() {
        return FileReadingMessageSource.class;
    }

    @Override
    public Class<FileV1InboundProperty> mappingKey() {
        return FileV1InboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.file_v1.name();
    }
}
