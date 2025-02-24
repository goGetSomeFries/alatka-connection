package com.alatka.connection.core.property.file;

import com.alatka.connection.core.property.core.ChannelAdapterProperty;

import javax.validation.constraints.NotNull;

public class FileTransformerHandlerProperty extends ChannelAdapterProperty {

    @NotNull
    private Type type;

    private String charset;

    private boolean deleteFiles;

    public enum Type {
        bytes, string
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isDeleteFiles() {
        return deleteFiles;
    }

    public void setDeleteFiles(boolean deleteFiles) {
        this.deleteFiles = deleteFiles;
    }
}
