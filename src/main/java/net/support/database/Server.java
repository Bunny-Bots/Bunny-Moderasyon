package net.support.database;

public class Server {

    private String serverid;
    private String channel;
    private String message;
    private String title;
    private String messageid;
    private String log;
    private String joinch;
    private int target;

    public Server(String serverid, String channel, String message, String title, String messageid, String log, String joinch, int target) {
        this.serverid = serverid;
        this.channel = channel;
        this.message = message;
        this.title = title;
        this.messageid = messageid;
        this.log = log;
        this.joinch = joinch;
        this.target = target;
    }

    public String getJoinch() {
        return joinch;
    }

    public void setJoinch(String joinch) {
        this.joinch = joinch;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
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
