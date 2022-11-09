package cc.causalmc.ccore.tags;

public enum Tags {

    WEED("420", "&7[&2420&7] ", 3000, "causal.tags.420", 1),
    NOOB("Noob", "&7[&eNoob&7] ", 3000, "causal.tags.noob", 2),
    DEPRESSION("._.", "&b._. ", 1000, "causal.tags.._.", 3),
    HEART("Heart", "&d<3 ", 1000, "causal.tags.heart", 4),
    CLOWN("Clown", "&7[&aC&cl&ao&cw&an&7] ", 3000, "causal.tags.clown", 5),
    BROKENHEART("Broken", "&d</3 ", 1000, "causal.tags.broken", 6),
    COVID("Covid", "&7[&2Covid&7] ", 3000, "causal.tags.covid", 7);

    int tagId;
    int price;
    String permission;
    String name;
    String prefix;

    Tags(String name, String prefix, int price, String permission, int tagId){
        this.name = name;
        this.prefix = prefix;
        this.price = price;
        this.permission = permission;
        this.tagId = tagId;
    }

    public static void load(){
        System.out.println("[CCore] Loading tags");
        for (Tags tag : Tags.values()) {
            System.out.println("[CCore] Loaded " + tag.getName());
        }
        System.out.println("[CCore] Loaded Tags");
    }

    public static String getTagPrefixByName(String name) {
        for (Tags tag : Tags.values()) {
            if(tag.getName().equalsIgnoreCase(name)) return tag.getPrefix();
        }
        return "";
    }
    public static Tags getTagByName(String name) {
        for (Tags tag : Tags.values()) {
            if(tag.getName().equalsIgnoreCase(name)) return tag;
        }
        return null;
    }

    public int getTagId() {
        return tagId;
    }

    public int getPrice() {
        return price;
    }

    public String getPermission() {
        return permission;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
