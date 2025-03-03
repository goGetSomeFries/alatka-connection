package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import javax.validation.constraints.NotNull;

/**
 * alatka.connection.flow.processors[n].handler.aggregator
 *
 * @author whocares
 */
public class AggregatorHandlerProperty extends ChannelAdapterProperty {

    @NotNull
    @IdentityProperty
    private String messageGroupProcessor;

    @IdentityProperty
    private String messageGroupStore;

    @IdentityProperty
    private String correlationStrategy;

    @IdentityProperty
    private String releaseStrategy;

    @IdentityProperty
    private String lockRegistry;

    @IdentityProperty
    private String discardChannel;

    private boolean releaseLockBeforeSend;

    private boolean expireGroupsUponCompletion;

    public String getMessageGroupProcessor() {
        return messageGroupProcessor;
    }

    public void setMessageGroupProcessor(String messageGroupProcessor) {
        this.messageGroupProcessor = messageGroupProcessor;
    }

    public String getMessageGroupStore() {
        return messageGroupStore;
    }

    public void setMessageGroupStore(String messageGroupStore) {
        this.messageGroupStore = messageGroupStore;
    }

    public String getCorrelationStrategy() {
        return correlationStrategy;
    }

    public void setCorrelationStrategy(String correlationStrategy) {
        this.correlationStrategy = correlationStrategy;
    }

    public String getReleaseStrategy() {
        return releaseStrategy;
    }

    public void setReleaseStrategy(String releaseStrategy) {
        this.releaseStrategy = releaseStrategy;
    }

    public String getLockRegistry() {
        return lockRegistry;
    }

    public void setLockRegistry(String lockRegistry) {
        this.lockRegistry = lockRegistry;
    }

    public String getDiscardChannel() {
        return discardChannel;
    }

    public void setDiscardChannel(String discardChannel) {
        this.discardChannel = discardChannel;
    }

    public boolean isReleaseLockBeforeSend() {
        return releaseLockBeforeSend;
    }

    public void setReleaseLockBeforeSend(boolean releaseLockBeforeSend) {
        this.releaseLockBeforeSend = releaseLockBeforeSend;
    }

    public boolean isExpireGroupsUponCompletion() {
        return expireGroupsUponCompletion;
    }

    public void setExpireGroupsUponCompletion(boolean expireGroupsUponCompletion) {
        this.expireGroupsUponCompletion = expireGroupsUponCompletion;
    }
}
