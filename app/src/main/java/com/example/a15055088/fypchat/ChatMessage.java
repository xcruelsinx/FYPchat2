package com.example.a15055088.fypchat;

import java.util.Random;
/**
public class ChatMessage {
    public String body, sender, receiver, senderName;
    public String Date, Time;
    public String msgid;
    public boolean isMine;// Did I send the message.

    public ChatMessage(String Sender, String Receiver, String messageString,
                       String ID, boolean isMINE) {
        body = messageString;
        isMine = isMINE;
        sender = Sender;
        msgid = ID;
        receiver = Receiver;
        senderName = sender;
    }

    public void setMsgID() {

        msgid += "-" + String.format("%02d", new Random().nextInt(100));
        ;
    }
}
*/
public class ChatMessage {
    public boolean isMine;
    public String message;

    public ChatMessage(boolean isMine, String message) {
        super();
        this.isMine = isMine;
        this.message = message;
    }
}