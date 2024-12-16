package com.ali.readersocket.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
    private String hash;
    private Integer value;
    private Timestamp timestamp;

    public Message(String hash, Integer value, Timestamp timestamp) {
        this.hash = hash;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Message{" +
                "hash='" + hash + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
