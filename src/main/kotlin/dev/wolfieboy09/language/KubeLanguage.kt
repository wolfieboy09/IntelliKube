package dev.wolfieboy09.language

import com.intellij.lang.Language

object KubeLanguage : Language("KubeJS"){
    private fun readResolve(): Any = KubeLanguage
}