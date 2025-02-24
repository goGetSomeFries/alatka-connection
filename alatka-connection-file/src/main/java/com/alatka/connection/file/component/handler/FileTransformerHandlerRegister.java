package com.alatka.connection.file.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.file.FileTransformerHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.file.transformer.AbstractFilePayloadTransformer;
import org.springframework.integration.file.transformer.FileToByteArrayTransformer;
import org.springframework.integration.file.transformer.FileToStringTransformer;

/**
 * TODO
 *
 * @author ybliu
 */
public class FileTransformerHandlerRegister extends HandlerComponentRegister<FileTransformerHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, FileTransformerHandlerProperty property) {
        builder.addPropertyValue("deleteFiles", property.isDeleteFiles());
        if (property.getType() == FileTransformerHandlerProperty.Type.string && property.getCharset() != null) {
            builder.addPropertyValue("charset", property.getCharset());
        }
    }

    @Override
    protected Class<? extends AbstractFilePayloadTransformer<?>> componentClass(FileTransformerHandlerProperty property) {
        return property.getType() == FileTransformerHandlerProperty.Type.bytes ?
                FileToByteArrayTransformer.class : FileToStringTransformer.class;
    }

    @Override
    public Class<FileTransformerHandlerProperty> mappingKey() {
        return FileTransformerHandlerProperty.class;
    }
}
