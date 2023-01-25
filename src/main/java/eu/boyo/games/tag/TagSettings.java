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

public class TagSettings {

    TagWeapon weapon;
    TagMode mode;

    public TagSettings(TagWeapon weapon, TagMode mode) {

    }

}
