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

    public TagSettings(TagWeapon weapon, TagMode mode) {

    }

}
