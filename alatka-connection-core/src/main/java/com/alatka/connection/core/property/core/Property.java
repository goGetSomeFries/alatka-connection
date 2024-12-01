package com.alatka.connection.core.property.core;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * @author ybliu
 */
public abstract class Property {

    /**
     * spring bean id
     */
    @IdentityProperty(referenced = false)
    private String id;

    /**
     * {@link Property} status
     */
    private boolean enabled = true;

    /**
     * {@link Property} 默认值
     *
     * @param <T> {@link Property}类型
     * @return {@link Property}类型对象
     */
    public <T extends Property> T defaultProperty() {
        throw new UnsupportedOperationException("不支持该方法调用");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String identity() {
        int index = this.id.indexOf(AlatkaConnectionConstant.IDENTITY_SEPARATOR);
        if (index == -1) {
            throw new IllegalArgumentException("identity not exist");
        }
        return this.id.substring(0, index + 1);
    }
}
