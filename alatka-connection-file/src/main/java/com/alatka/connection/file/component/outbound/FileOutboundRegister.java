package com.alatka.connection.file.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.property.file.FileOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.config.FileWritingMessageHandlerFactoryBean;
import org.springframework.integration.file.support.FileExistsMode;

/**
 * TODO
 *
 * @author ybliu
 */
public class FileOutboundRegister extends OutboundComponentRegister<FileOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, FileOutboundProperty property) {
        DefaultFileNameGenerator fileNameGenerator = new DefaultFileNameGenerator();
        fileNameGenerator.setExpression(property.getFileNameExpression());
        builder.addPropertyValue("fileNameGenerator", fileNameGenerator);

        Expression directoryExpression = new SpelExpressionParser().parseExpression(property.getDirectoryExpression());
        builder.addPropertyValue("directoryExpression", directoryExpression);

        if (property.getTemporaryFileSuffix() != null) {
            builder.addPropertyValue("temporaryFileSuffix", property.getTemporaryFileSuffix());
        }
        if (property.getFileExistsMode() != null) {
            builder.addPropertyValue("fileExistsMode", FileExistsMode.getForString(property.getFileExistsMode()));
        }
        if (property.getChmod() != null) {
            builder.addPropertyValue("chmod", property.getChmod());
        }
        if (property.getCharset() != null) {
            builder.addPropertyValue("charset", property.getCharset());
        }

        builder.addPropertyValue("autoCreateDirectory", property.isAutoCreateDirectory());
        builder.addPropertyValue("deleteSourceFiles", property.isDeleteSourceFiles());
    }

    @Override
    protected Class<FileWritingMessageHandlerFactoryBean> componentClass(FileOutboundProperty property) {
        return FileWritingMessageHandlerFactoryBean.class;
    }

    @Override
    public Class<FileOutboundProperty> mappingKey() {
        return FileOutboundProperty.class;
    }

}
