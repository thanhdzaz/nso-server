package com.serve.io;

public interface ISession {
    boolean isConnected();

    void sendMessage(Message message);

    void close();
}
