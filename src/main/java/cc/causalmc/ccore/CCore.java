package cc.causalmc.ccore;

import cc.causalmc.ccore.commands.arguments.mod.*;
import cc.causalmc.ccore.commands.arguments.player.*;
import cc.causalmc.ccore.commands.arguments.admin.*;
import cc.causalmc.ccore.commands.manager.CommandManager;
import cc.causalmc.ccore.listeners.*;
import cc.causalmc.ccore.backend.Backend;
import cc.causalmc.ccore.tags.Tags;
import cc.causalmc.ccore.utils.ConfigFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CCore extends JavaPlugin {


    @Getter
    public Backend backend;
    @Getter
    private static CCore Instance;
    private ConfigFile config;
    public HashMap<String, String> grantMap= new HashMap<>();
    public List<UUID> invPlayers = new ArrayList<>();
    public String rank = "";
    public String time = "";
    public static Map<UUID, String> buyInv = new HashMap<>();
    @Getter public CoreAPI api;


    @Override
    public void onEnable() {
        Instance = this;
        getLogger().info("Registering Events");
        registerEvents();
        getLogger().info("Registering Config File (useless)");
        config = new ConfigFile(this, "config.yml");
        getLogger().info("Registering Commands");
        new CommandManager(this);
        getLogger().info("Connecting to the database");
        try {
            backend = new Backend();
        } catch (Exception e) {
            getLogger().severe("Cannot connect to MongoDB... Disabling plugin");
            getPluginLoader().disablePlugin(this);
            e.printStackTrace();
        }
        Tags.load();
        getLogger().info("Creating the Api");
        api = new CoreAPI();
        getLogger().info("Plugin Successfully Started!");
    }


    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new GrantCommand(this), this);
        getServer().getPluginManager().registerEvents(new InvseeCommand(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new DatabaseJoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new BuyInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new TagsCommand(this), this);
        getServer().getPluginManager().registerEvents(new CosmeticsCommand(this), this);
    }

    @Override
    public ConfigFile getConfig() {
        return config;
    }
}
