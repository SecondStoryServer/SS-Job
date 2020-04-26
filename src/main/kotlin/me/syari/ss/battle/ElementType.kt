package me.syari.ss.battle

enum class ElementType(val display: String) {
    Fire("火"),
    Water("水"),
    Wood("木"),
    Holy("聖"),
    Dark("闇");

    companion object {
        private val elementEffect by lazy {
            mapOf(
                    Fire to Water to 0.8F,
                    Fire to Wood to 1.5F,
                    Water to Wood to 0.8F,
                    Water to Fire to 1.5F,
                    Wood to Fire to 0.8F,
                    Wood to Water to 1.5F,
                    Dark to Fire to 1.3F,
                    Dark to Water to 1.3F,
                    Dark to Wood to 1.3F,
                    Dark to Holy to 0.5F,
                    Holy to Dark to 2.0F
            )
        }

        fun getEffectDamage(attack: ElementType, defense: ElementType): Float {
            return elementEffect.getOrDefault(attack to defense, 1.0F)
        }
    }
}