package cc.causalmc.ccore.backend;

import cc.causalmc.ccore.CCore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;

import javax.print.Doc;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseManager {
    private final static Gson gson = new GsonBuilder().serializeNulls().create();
    @SuppressWarnings("unused")
    public static void loadAccount(UUID uuid) {
        CompletableFuture.runAsync(() -> {
            if (CCore.getInstance().getBackend().getCollection().find(new Document("uuid", uuid.toString())).first() == null){
                try {
                    Document profile = new Document("_id", uuid.toString())
                            .append("uuid", uuid.toString())
                            .append("coins", 0)
                            .append("credits", 0)
                            .append("tag", null);
                    CCore.getInstance().getBackend().getCollection().insertOne(profile);
                    CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> jedis.hset("profile", uuid.toString(), gson.toJson(profile)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
                    Document profile = (Document) CCore.getInstance().getBackend().getCollection().find(new Document("uuid", uuid.toString())).first();
                    jedis.hset("profile", uuid.toString(), gson.toJson(profile));
                });
            }
        });

    }
    /*public static CompletableFuture<Object> getFromCollection(UUID uuid, String string){
         return CompletableFuture.supplyAsync(() -> {
             try {
                 DBObject q = new BasicDBObject("_id", uuid.toString());
                 DBObject r = CCore.getInstance().getBackend().getCollection().find(q).one();

                 return r.get(string);
             } catch (Exception e) {
                 e.printStackTrace();
                 return null;
             }
         });
    }*/


    public static Object getFromCollection(UUID uuid, String string) {
        final AtomicReference<Object> toReturn = new AtomicReference<>();
        try {
            CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
                String s = jedis.hget("profile", uuid.toString());
                Document dbObject = gson.fromJson(s, Document.class);
                toReturn.set(dbObject.get(string));
            });
            /*
            DBObject q = new BasicDBObject("_id", uuid.toString());
            DBObject r = CCore.getInstance().getBackend().getCollection().find(q).one();
            */
            return toReturn.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
