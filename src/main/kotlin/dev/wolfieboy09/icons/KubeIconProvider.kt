package dev.wolfieboy09.icons

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class KubeIconProvider : FileIconProvider {
    private val kubeFiles = mapOf(
        "kubejs.plugins.txt" to KubeIcons.DEFAULT,
    )

    override fun getIcon(file: VirtualFile, p1: Int, p2: Project?): Icon? = kubeFiles[file.name]
}