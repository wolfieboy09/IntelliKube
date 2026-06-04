package dev.wolfieboy09.icons

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class KubeIconProvider : FileIconProvider {

    // Just to make sure if a custom icon for specific files become a thing, and also to make sure
    // files that don't start with "kubejs" or contain it get the kube icon
    private val kubeFiles = mapOf(
        "kubejs.plugins.txt" to KubeIcons.DEFAULT,
        "kubejs.bindings.txt" to KubeIcons.DEFAULT,
    )

    override fun getIcon(file: VirtualFile, p1: Int, p2: Project?): Icon? = kubeFiles[file.name]
}