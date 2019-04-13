package com.example.writerssden;

public class submitart {
    String contentID;
    String contentuser;
    String contentHead;
    String contentContent;

    public submitart(String contentID, String contentuser, String contentHead, String contentContent) {
        this.contentID = contentID;
        this.contentuser = contentuser;
        this.contentHead = contentHead;
        this.contentContent = contentContent;
    }
    public String getcontentID() {
        return contentID;
    }
    public String getcontentuser() {
        return contentuser;
    }
    public String getContentHead() {
        return contentHead;
    }

    public String getContentContent() {
        return contentContent;
    }
}

