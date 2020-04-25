package me.syari.ss.battle

enum class ElementType(val display: String) {
    Fire("火"),
    Water("水"),
    Wood("木"),
    Holy("聖"),
    Dark("闇");

    companion object {
        const val weakDamageRate = 0.8F
        const val strongDamageRate = 1.5F

        private val weakToElementType by lazy {
            mapOf(
                Fire to Water,
                Water to Wood,
                Wood to Fire,
                Holy to Dark,
                Dark to Holy
            )
        }
    }
}