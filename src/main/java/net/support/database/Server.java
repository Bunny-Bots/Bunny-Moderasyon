package net.support.database;

public class Server {

    private String serverid;
    private String channel;
    private String  message;
    private String title;
    private String messageid;
    private String log;

    public Server(String serverid, String channel, String message, String title, String messageid, String log){
       this.serverid = serverid;
       this.channel = channel;
       this.message = message;
       this.title = title;
       this.messageid = messageid;
       this.log = log;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getServerId() {
        return serverid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
