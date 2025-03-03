package com.alatka.connection.core.property.file;

import com.alatka.connection.core.property.core.ChannelAdapterProperty;

/**
 * alatka.connection.flow.processors[n].handler.file_splitter
 *
 * @author whocares
 */
public class FileSplitterHandlerProperty extends ChannelAdapterProperty {

    private String charset;

    private String firstLineAsHeader;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFirstLineAsHeader() {
        return firstLineAsHeader;
    }

    public void setFirstLineAsHeader(String firstLineAsHeader) {
        this.firstLineAsHeader = firstLineAsHeader;
    }
}
