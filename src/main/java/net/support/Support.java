package net.support;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.support.database.MongoDatabaseController;
import net.support.database.Server;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

final public class Support {

    public static Gson gson = new Gson();
    private static org.apache.log4j.Logger log
            = Logger.getLogger(Support.class);
    public static JDA jda;
    private CommandMap cmd;
    public static MongoDatabaseController client;

    public Support() throws FileNotFoundException, LoginException, Exception {
        cmd = new CommandMap();
        jda = new JDABuilder()
                .setToken("NzMzNzIxMzcyMjA2ODI1NTAy.XxHRWQ.ByTtTE3QVXzHUp21l1a_UVGLLQA")
                .setActivity(Activity.of(Activity.ActivityType.fromKey(0), "Support Bot"))
                .setStatus(OnlineStatus.ONLINE)
                .build();
        jda.addEventListener(new Listener(this));
       client = new MongoDatabaseController(27017, "mirac", "tygr.34", "support.elj9p.mongodb.net", "admin");
    }

    public static Server getProfile(String id){
        DB db = client.getDB();
        DBCollection collection = db.getCollection("server-info");
        DBObject object = client.findObjectBy(collection, id,"serverid");
        return gson.fromJson(object.toString(), Server.class);
    }

    public static void saveProfile(Server server){
        String p = gson.toJson(server);
        DB db = client.getDB();
        DBCollection collection = db.getCollection("server-info");
        DBObject ob = (DBObject) JSON.parse(p);
        DBObject object = new BasicDBObject().append("serverid", server.getServerId());
        if (collection.findOne(object) == null){
            collection.insert(ob);
        } else {
            collection.update(object,ob);
        }
    }

    public static void main(String[] args) throws Exception {
        log.info("Deneme");
        new Support();
    }

    public static JDA getJda() {
        return jda;
    }

    public CommandMap getCommandMap(){
        return cmd;
    }

}
