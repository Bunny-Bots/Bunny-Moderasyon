package net.support.database;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class MongoDatabaseController {
    private MongoClient client;
    private int port;
    private String username;
    private String password;
    private String hostname;
    private String database;

    private DB db;

    public MongoDatabaseController(int port, String username, String password, String hostname, String database) throws Exception {
        this.port = port;
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.database = database;
        init();
    }

    public void init() throws Exception {
        String encoded;
        encoded = URLEncoder.encode(password, "UTF-8");
        String client_url = "mongodb://" + username + ":" + encoded + "@" + hostname + ":" + port + "/" + database;
        MongoClient client = new MongoClient("localhost", 27017);
        db = client.getDB("bunny-mod");
        try {
            client.getAddress();
            System.out.println("Database connection was established!");
        } catch (Exception e) {
            System.out.println("Cant connect to database!");

            client.close();
            throw e;
        }
    }





    public DB getDB(String name) {
        return getClient().getDB(name);
    }

    public DBCollection getCollection(DB db, String coll) {
        return db.getCollection(coll);
    }

    public DBCollection getCollection(String db, String coll) {
        return getDB(db).getCollection(coll);
    }

    public DBObject findObjectById(DBCollection collection, String sid) {
        return collection.findOne(new BasicDBObject().append("_id", sid));
    }
    public DBObject findObjectBy(DBCollection collection, String sid, String by) {
        return collection.findOne(new BasicDBObject().append(by, sid));
    }

    public DB getDB() {
        return db;
    }

    public MongoClient getClient() {
        return client;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHostname() {
        return hostname;
    }

    public String getDatabase() {
        return database;
    }
}
