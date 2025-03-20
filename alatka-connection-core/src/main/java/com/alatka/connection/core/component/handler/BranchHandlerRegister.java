package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.property.core.BranchHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.messaging.Message;

/**
 * 分支流程组件注册器
 *
 * @author ybliu
 * @see BranchMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#branch
 */
public class BranchHandlerRegister extends HandlerComponentRegister<BranchHandlerProperty> {

    @Override
    public void doRegister(BeanDefinitionBuilder builder, BranchHandlerProperty property) {
        if (property.getTaskExecutor() != null) {
            builder.addPropertyReference("taskExecutor", property.getTaskExecutor());
        }

        String branchChannel = property.getChannel() == null ?
                AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL : property.getChannel();
        builder.addPropertyValue("branchChannelName",
                property.getIdentity() + AlatkaConnectionConstant.IDENTITY_SEPARATOR + branchChannel);
    }

    @Override
    protected Class<BranchMessageHandler> componentClass(BranchHandlerProperty property) {
        return BranchMessageHandler.class;
    }

    @Override
    public Class<BranchHandlerProperty> mappingKey() {
        return BranchHandlerProperty.class;
    }

    public static class BranchMessageHandler extends AbstractReplyProducingMessageHandler {

        private String branchChannelName;

        private TaskExecutor taskExecutor = new SyncTaskExecutor();

        @Override
        protected Object handleRequestMessage(Message<?> requestMessage) {
            taskExecutor.execute(() -> messagingTemplate.send(branchChannelName, requestMessage));
            return requestMessage;
        }

        public void setBranchChannelName(String branchChannelName) {
            this.branchChannelName = branchChannelName;
        }

        public void setTaskExecutor(TaskExecutor taskExecutor) {
            this.taskExecutor = taskExecutor;
        }
    }
}
