package cc.causalmc.ccore.backend;

import cc.causalmc.ccore.CCore;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.zowpy.jedisapi.JedisAPI;
import io.github.zowpy.jedisapi.redis.RedisCredentials;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.entity.Player;

public class Backend {

    @Getter public final MongoClient mongoClient;
    @Getter private final MongoDatabase database;
    @Getter private final MongoCollection collection;
    @Getter private final RedisCredentials credentials;
    @Getter private final JedisAPI jedis;




    public Backend() throws Exception {

        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDatabase("Causal");
        collection = database.getCollection("CausalPlayer");
        credentials = new RedisCredentials("localhost", "", "cCoreSave", 6379, false);
        jedis = new JedisAPI(credentials);

    }

    public boolean collectionExists(final String collectionName) {
        return database.getCollection(collectionName) != null;
    }

    public static Object getFromCollection(Player player, String string) {
        Document query = new Document("name", player.getName());
        Document cplayer = (Document) CCore.getInstance().getBackend().getCollection().find(query).first();
        return cplayer.get(string);
    }

}
