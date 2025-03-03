package com.alatka.connection.core.property.file;

import com.alatka.connection.core.property.core.OutboundProperty;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.outbound.file
 *
 * @author ybliu
 */
public class FileOutboundProperty extends OutboundProperty {

    @NotEmpty
    private String directoryExpression;

    @NotEmpty
    private String fileNameExpression;

    private boolean autoCreateDirectory = true;

    private boolean deleteSourceFiles;

    private String chmod;

    private String fileExistsMode;

    private String temporaryFileSuffix;

    private String charset;

    public String getDirectoryExpression() {
        return directoryExpression;
    }

    public void setDirectoryExpression(String directoryExpression) {
        this.directoryExpression = directoryExpression;
    }

    public String getFileNameExpression() {
        return fileNameExpression;
    }

    public void setFileNameExpression(String fileNameExpression) {
        this.fileNameExpression = fileNameExpression;
    }

    public boolean isAutoCreateDirectory() {
        return autoCreateDirectory;
    }

    public void setAutoCreateDirectory(boolean autoCreateDirectory) {
        this.autoCreateDirectory = autoCreateDirectory;
    }

    public boolean isDeleteSourceFiles() {
        return deleteSourceFiles;
    }

    public void setDeleteSourceFiles(boolean deleteSourceFiles) {
        this.deleteSourceFiles = deleteSourceFiles;
    }

    public String getChmod() {
        return chmod;
    }

    public void setChmod(String chmod) {
        this.chmod = chmod;
    }

    public String getFileExistsMode() {
        return fileExistsMode;
    }

    public void setFileExistsMode(String fileExistsMode) {
        this.fileExistsMode = fileExistsMode;
    }

    public String getTemporaryFileSuffix() {
        return temporaryFileSuffix;
    }

    public void setTemporaryFileSuffix(String temporaryFileSuffix) {
        this.temporaryFileSuffix = temporaryFileSuffix;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
