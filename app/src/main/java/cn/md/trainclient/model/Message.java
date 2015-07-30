package cn.md.trainclient.model;

import java.io.Serializable;

/**
 * User: su
 * Date: 2015-07-14.
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1905286596999355749L;

    private String avatar;
    private String senderName;
    private String receiverName;
    private String content;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
