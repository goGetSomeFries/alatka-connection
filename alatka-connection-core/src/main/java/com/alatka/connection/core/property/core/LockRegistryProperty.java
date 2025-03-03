package com.alatka.connection.core.property.core;

/**
 * alatka.connection.definition.lockRegistries
 *
 * @author ybliu
 * @see org.springframework.integration.support.locks.LockRegistry
 */
public class LockRegistryProperty extends MultiTypeSupportProperty {

    public enum Type implements MultiTypeSupportProperty.Type {

        fallback(Fallback.class),
        passthrough(Params.class);

        private final Class<? extends Params> clazz;

        Type(Class<? extends Params> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Class<? extends Params> getClazz() {
            return clazz;
        }

    }

    @Override
    public Type valueOf(String type) {
        return Type.valueOf(type);
    }

    public static class Fallback extends Params {

        private Integer mask;

        public Integer getMask() {
            return mask;
        }

        public void setMask(Integer mask) {
            this.mask = mask;
        }
    }
}
