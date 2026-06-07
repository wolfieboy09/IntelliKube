package dev.wolfieboy09.intellikube.icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object KubeIcons {
    val DEFAULT: Icon = icon("kubejs")
    val PLUGIN: Icon = icon("kubejs_plugin")

    private fun icon(name: String) = IconLoader.getIcon("icons/$name.svg", KubeIcons::class.java)
}