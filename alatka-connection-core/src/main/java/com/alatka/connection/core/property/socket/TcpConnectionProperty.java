package com.alatka.connection.core.property.socket;

import com.alatka.connection.core.property.Property;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TcpConnectionProperty extends Property {

    private boolean server;
    private String host;
    @Min(0)
    @Max(65535)
    private Integer port;
    private boolean usingNio = false;
    private boolean usingDirectBuffers = false;
    private boolean singleUse = false;

    private Integer backlog = 5;
    private boolean soKeepAlive = false;
    private Integer soLinger = -1;
    private Integer soReceiveBufferSize;
    private Integer soSendBufferSize;
    private boolean soTcpNoDelay = false;
    private Integer connectTimeout = 60000;
    private Integer readTimeout = -1;

    @NotEmpty
    private String serializer;
    @NotEmpty
    private String deserializer;
    @NotEmpty
    private String tcpMessageMapper;

    private String taskExecutor;

    public boolean isServer() {
        return server;
    }

    public void setServer(boolean server) {
        this.server = server;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isUsingNio() {
        return usingNio;
    }

    public void setUsingNio(boolean usingNio) {
        this.usingNio = usingNio;
    }

    public boolean isUsingDirectBuffers() {
        return usingDirectBuffers;
    }

    public void setUsingDirectBuffers(boolean usingDirectBuffers) {
        this.usingDirectBuffers = usingDirectBuffers;
    }

    public boolean isSingleUse() {
        return singleUse;
    }

    public void setSingleUse(boolean singleUse) {
        this.singleUse = singleUse;
    }

    public Integer getBacklog() {
        return backlog;
    }

    public void setBacklog(Integer backlog) {
        this.backlog = backlog;
    }

    public boolean isSoKeepAlive() {
        return soKeepAlive;
    }

    public void setSoKeepAlive(boolean soKeepAlive) {
        this.soKeepAlive = soKeepAlive;
    }

    public Integer getSoLinger() {
        return soLinger;
    }

    public void setSoLinger(Integer soLinger) {
        this.soLinger = soLinger;
    }

    public Integer getSoReceiveBufferSize() {
        return soReceiveBufferSize;
    }

    public void setSoReceiveBufferSize(Integer soReceiveBufferSize) {
        this.soReceiveBufferSize = soReceiveBufferSize;
    }

    public Integer getSoSendBufferSize() {
        return soSendBufferSize;
    }

    public void setSoSendBufferSize(Integer soSendBufferSize) {
        this.soSendBufferSize = soSendBufferSize;
    }

    public boolean isSoTcpNoDelay() {
        return soTcpNoDelay;
    }

    public void setSoTcpNoDelay(boolean soTcpNoDelay) {
        this.soTcpNoDelay = soTcpNoDelay;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getSerializer() {
        return serializer;
    }

    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }

    public String getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(String deserializer) {
        this.deserializer = deserializer;
    }

    public String getTcpMessageMapper() {
        return tcpMessageMapper;
    }

    public void setTcpMessageMapper(String tcpMessageMapper) {
        this.tcpMessageMapper = tcpMessageMapper;
    }

    public String getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(String taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
