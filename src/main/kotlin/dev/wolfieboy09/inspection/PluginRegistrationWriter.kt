package dev.wolfieboy09.inspection

import com.intellij.codeInsight.intention.preview.IntentionPreviewInfo
import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException

class PluginRegistrationWriter(
    private val fqn: String,
    private val registryFile: VirtualFile?,
    private val resourcesRoot: VirtualFile?
) : LocalQuickFix {

    override fun getName() = if (registryFile != null)
        "Add '${fqn.substringAfterLast('.')}' to ${registryFile.name}"
    else
        "Create ${PluginRegistrationInspection.REGISTRATION_FILE} and register '${fqn.substringAfterLast('.')}'"

    override fun getFamilyName() = "KubeJS Plugin registration"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        WriteCommandAction.runWriteCommandAction(project, "KubeJS Plugin Registration", null, {
            try {
                val file = registryFile
                    ?: resourcesRoot?.createChildData(this, PluginRegistrationInspection.REGISTRATION_FILE)
                    ?: run {
                        Messages.showErrorDialog(
                            project,
                            "Could not find a resources source root.",
                            "IntelliKube: Plugin Registration Error"
                        )
                        return@runWriteCommandAction
                    }

                RegistryFileUtil.appendEntry(file, fqn)
            } catch (e: IOException) {
                Messages.showErrorDialog(
                    project,
                    "Could not write to ${PluginRegistrationInspection.REGISTRATION_FILE}:\n\n${e.message}",
                    "IntelliKube: Plugin Registration Error"
                )
            }
        })
    }

    // Silences "Field may prevent intention preview from working properly"
    override fun generatePreview(project: Project, previewDescriptor: ProblemDescriptor): IntentionPreviewInfo = IntentionPreviewInfo.EMPTY
}