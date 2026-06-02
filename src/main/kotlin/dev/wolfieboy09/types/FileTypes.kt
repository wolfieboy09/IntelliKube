package dev.wolfieboy09.types

import com.intellij.openapi.fileTypes.LanguageFileType
import dev.wolfieboy09.icons.KubeIcons
import dev.wolfieboy09.language.KubeLanguage
import javax.swing.Icon

class FileTypes : LanguageFileType(KubeLanguage) {
    override fun getName(): String = "KubeJS"
    override fun getDescription(): String = "KubeJS script file"
    override fun getDefaultExtension(): String = "kubejs.plugins.txt"
    override fun getIcon(): Icon = KubeIcons.DEFAULT
}