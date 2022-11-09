package cc.causalmc.ccore.backend;

import cc.causalmc.ccore.CCore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.bson.Document;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerData {

    private UUID uuid;
    private final Gson gson = new GsonBuilder().serializeNulls().create();
    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public void addCoins(double amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getBackend().getCollection().updateOne(new Document("_id", uuid.toString()),
                new Document("$inc", new Document("coins", amount))));
        CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
            String s = jedis.hget("profile", uuid.toString());
            Document dbObject = gson.fromJson(s, Document.class);
            dbObject = new Document("_id", uuid.toString())
                    .append("uuid", uuid.toString())
                    .append("coins", (double) dbObject.get("coins") + amount)
                    .append("credits",dbObject.get("credits"))
                    .append("tag", dbObject.get("tag"));
            jedis.hset("profile", uuid.toString(), gson.toJson(dbObject));
        });
    }
    public void removeCoins(double amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getBackend().getCollection().updateOne(new Document("_id", uuid.toString()),
                new Document("$inc", new Document("coins", -amount))));
        CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
            String s = jedis.hget("profile", uuid.toString());
            Document dbObject = gson.fromJson(s, Document.class);
            dbObject = new Document("_id", uuid.toString())
                    .append("uuid", uuid.toString())
                    .append("coins", (double) dbObject.get("coins") - amount)
                    .append("credits",dbObject.get("credits"))
                    .append("tag", dbObject.get("tag"));
            jedis.hset("profile", uuid.toString(), gson.toJson(dbObject));
        });
    }
    public double getCoins(){
        return (double) DatabaseManager.getFromCollection(uuid, "coins");
    }
    public void addCredits(double amount) {
        CompletableFuture.runAsync(() ->  {
            CCore.getInstance().getBackend().getCollection().updateOne(new Document("_id", uuid.toString()),
                new Document("$inc", new Document("credits", amount)));
            CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
                String s = jedis.hget("profile", uuid.toString());
                Document dbObject = gson.fromJson(s, Document.class);
                dbObject = new Document("_id", uuid.toString())
                        .append("uuid", uuid.toString())
                        .append("coins", dbObject.get("coins"))
                        .append("credits", (double) dbObject.get("credits") + amount)
                        .append("tag", dbObject.get("tag"));
                jedis.hset("profile", uuid.toString(), gson.toJson(dbObject));
            });
        });
    }
    public void removeCredits(double amount){
        CompletableFuture.runAsync(() -> CCore.getInstance().getBackend().getCollection().updateOne(new Document("_id", uuid.toString()),
                new Document("$inc", new Document("credits", -amount))));
        CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
            String s = jedis.hget("profile", uuid.toString());
            Document dbObject = gson.fromJson(s, Document.class);
            dbObject = new Document("_id", uuid.toString())
                    .append("uuid", uuid.toString())
                    .append("coins", dbObject.get("coins"))
                    .append("credits", (double) dbObject.get("credits") - amount)
                    .append("tag", dbObject.get("tag"));
            jedis.hset("profile", uuid.toString(), gson.toJson(dbObject));
        });
    }
    public double getCredits(){
       return (double) DatabaseManager.getFromCollection(uuid, "credits");
    }
    public String getTag(){
        if(DatabaseManager.getFromCollection(uuid, "tag") == null || (String) DatabaseManager.getFromCollection(uuid, "tag") == ""){
            return "";
        }else {
            return (String) DatabaseManager.getFromCollection(uuid, "tag");
        }
    }
    public void setTag(String name) {
        CompletableFuture.runAsync(() -> CCore.getInstance().getBackend().getCollection().updateOne(new Document("_id", uuid.toString()),
                new Document("$set", new Document("tag", name))));
        CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
            String s = jedis.hget("profile", uuid.toString());
            Document dbObject = gson.fromJson(s, Document.class);
            dbObject = new Document("_id", uuid.toString())
                    .append("uuid", uuid.toString())
                    .append("coins", dbObject.get("coins"))
                    .append("credits", dbObject.get("credits"))
                    .append("tag", name);
            jedis.hset("profile", uuid.toString(), gson.toJson(dbObject));
        });
    }
    public void resetTag() {
        CompletableFuture.runAsync(() -> CCore.getInstance().getBackend().getCollection().updateOne(new Document("_id", uuid.toString()),
                new Document("$set", new Document("tag", ""))));
        CCore.getInstance().getBackend().getJedis().getJedisHandler().runCommand(jedis -> {
            String s = jedis.hget("profile", uuid.toString());
            Document dbObject = gson.fromJson(s, Document.class);
            dbObject = new Document("_id", uuid.toString())
                    .append("uuid", uuid.toString())
                    .append("coins", dbObject.get("coins"))
                    .append("credits",dbObject.get("credits"))
                    .append("tag", "");
            jedis.hset("profile", uuid.toString(), gson.toJson(dbObject));
        });
    }

}
