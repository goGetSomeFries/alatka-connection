package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.module.ChannelModuleBuilder;
import com.alatka.connection.core.module.ChannelModuleBuilderAware;
import com.alatka.connection.core.property.core.ChannelProperty;
import com.alatka.connection.core.property.core.RouterOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.filter.ExpressionEvaluatingSelector;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.support.channel.ChannelResolverUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author ybliu
 */
public class RouterOutboundRegister extends OutboundComponentRegister<RouterOutboundProperty> implements ChannelModuleBuilderAware {

    private ChannelModuleBuilder channelModuleBuilder;

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, RouterOutboundProperty property) {
        List<RouterOutboundProperty.RouterProperty> items = property.getItems();

        List<RecipientListRouter.Recipient> recipients = items.stream()
                .peek(this::init)
                .map(this::buildRecipient)
                .collect(Collectors.toList());
        builder.addPropertyValue("recipients", recipients);

        String defaultChannel = property.getDefaultChannel() == null ?
                DefaultConfig.FALLBACK_NULL_CHANNEL : property.getDefaultChannel();
        builder.addPropertyValue("defaultOutputChannelName", defaultChannel);
    }

    private void init(RouterOutboundProperty.RouterProperty property) {
        if (!property.isChannelExisted()) {
            ChannelProperty channel = new ChannelProperty();
            channel.setId(property.getChannel());
            channel.setType(ChannelProperty.Type.direct);
            channelModuleBuilder.build(channel);
            property.setChannel(channelModuleBuilder.getBeanName());
        }
    }

    private RecipientListRouter.Recipient buildRecipient(RouterOutboundProperty.RouterProperty property) {
        ExpressionEvaluatingSelector selector = null;
        if (property.getExpression() != null) {
            selector = new ExpressionEvaluatingSelector(property.getExpression());
            selector.setBeanFactory(getBeanFactory());
        }

        RecipientListRouter.Recipient recipient = new RecipientListRouter.Recipient(property.getChannel(), selector);
        recipient.setChannelResolver(ChannelResolverUtils.getChannelResolver(getBeanFactory()));
        return recipient;
    }

    @Override
    protected Class<RecipientListRouter> componentClass() {
        return RecipientListRouter.class;
    }

    @Override
    public Class<RouterOutboundProperty> mappingKey() {
        return RouterOutboundProperty.class;
    }

    @Override
    public void setChannelModuleBuilder(ChannelModuleBuilder channelModuleBuilder) {
        this.channelModuleBuilder = channelModuleBuilder;
    }
}
