package com.alatka.connection.example.example1;

public class ReqMess {

    private String seq;

    private String topic;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "ReqMess{" +
                "seq='" + seq + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
