package com.yy.app.event;

import android.support.annotation.NonNull;

public class MyEvent {
    private String type;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
