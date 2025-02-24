package com.alatka.connection.file.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.file.FileSplitterHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.file.splitter.FileSplitter;

/**
 * TODO
 *
 * @author ybliu
 */
public class FileSplitterHandlerRegister extends HandlerComponentRegister<FileSplitterHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, FileSplitterHandlerProperty property) {
        if (property.getCharset() != null) {
            builder.addPropertyValue("charset", property.getCharset());
        }
        if (property.getFirstLineAsHeader() != null) {
            builder.addPropertyValue("firstLineAsHeader", property.getFirstLineAsHeader());
        }
    }

    @Override
    protected Class<FileSplitter> componentClass(FileSplitterHandlerProperty property) {
        return FileSplitter.class;
    }

    @Override
    public Class<FileSplitterHandlerProperty> mappingKey() {
        return FileSplitterHandlerProperty.class;
    }
}
