package dev.wolfieboy09.inspection

import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiClass
import java.io.IOException
import java.nio.charset.StandardCharsets

object RegistryFileUtil {

    fun findRegistryFile(psiClass: PsiClass): VirtualFile? {
        val resourcesRoot = findResourcesRoot(psiClass) ?: return null
        return resourcesRoot.findFileByRelativePath(PluginRegistrationInspection.REGISTRATION_FILE)
    }

    fun findResourcesRoot(psiClass: PsiClass): VirtualFile? {
        val module = ModuleUtilCore.findModuleForPsiElement(psiClass) ?: return null

        return ModuleRootManager.getInstance(module)
            .getSourceRoots(false)
            .firstOrNull { root ->
                root.path.endsWith("resources") ||
                        root.path.contains("/resources/") ||
                        root.path.contains("\\resources\\")
            }
    }

    fun isRegistered(file: VirtualFile, fqn: String): Boolean {
        return try {
            val content = file.contentsToByteArray().toString(StandardCharsets.UTF_8)
            content.lineSequence()
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .any { line -> line == fqn || line.startsWith("$fqn ") }
        } catch (_: IOException) {
            false
        }
    }

    fun appendEntry(file: VirtualFile, fqn: String) {
        val existing = try {
            file.contentsToByteArray().toString(StandardCharsets.UTF_8)
        } catch (_: IOException) {
            ""
        }

        val newContent = buildString {
            append(existing)
            if (existing.isNotEmpty() && !existing.endsWith('\n')) append('\n')
            append(fqn)
        }

        file.setBinaryContent(newContent.toByteArray(StandardCharsets.UTF_8))
    }
}