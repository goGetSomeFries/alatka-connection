package com.alatka.connection.example.example1;

public class RespMess {

    private String msg;
    private String status;

    public RespMess(String status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespMess{" +
                "msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
