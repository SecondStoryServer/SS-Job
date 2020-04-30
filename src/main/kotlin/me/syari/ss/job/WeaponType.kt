package me.syari.ss.job

enum class WeaponType(
    val id: String, val display: String
) {
    Sword(
        "sword", "剣"
    ),
    Axe(
        "axe", "斧"
    ),
    Bow(
        "bow", "弓"
    ),
    Knife(
        "knife", "短剣"
    ),
    Wand(
        "wand", "杖"
    ),
    Mace(
        "mace", "棍"
    );

    companion object {
        fun getById(id: String): WeaponType? {
            return values().firstOrNull { it.id == id }
        }
    }
}