package eu.boyo.games.tag;

enum TagWeapon {
    DEFAULT,
    BOW
}
enum TagMode {
    DEFAULT,
    FAMILY,
    HOT_POTATO
}
enum TagMap {
    RANDOM,
    JUNGLE,
    DESERT,
    CONSTRUCTION_SITE
}

public class TagSettings {

    TagWeapon weapon;
    TagMode mode;
    TagMap map;
    byte gracePeriod;
    short gameLength;
    boolean runnerPvp;
    boolean tagbacks;

    public TagSettings(TagWeapon weapon, TagMode mode, TagMap mapOverride, byte gracePeriod, short gameLength, boolean runnerPvp, boolean tagbacks) {

    }

}
