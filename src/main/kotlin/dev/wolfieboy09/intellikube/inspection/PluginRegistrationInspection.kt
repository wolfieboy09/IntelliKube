package dev.wolfieboy09.intellikube.inspection

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiModifier
import com.intellij.psi.util.InheritanceUtil
import com.intellij.uast.UastVisitorAdapter
import org.jetbrains.uast.UClass
import org.jetbrains.uast.visitor.AbstractUastNonRecursiveVisitor

class PluginRegistrationInspection : AbstractBaseJavaLocalInspectionTool() {
    companion object {
        const val TARGET_INTERFACE = "dev.latvian.mods.kubejs.plugin.KubeJSPlugin"
        const val REGISTRATION_FILE = "kubejs.plugins.txt"
    }

    override fun getDisplayName() = "Plugin not registered with KubeJS"
    override fun getGroupDisplayName() = "KubeJS plugin"
    override fun getShortName() = "ModHandlerNotRegistered"

    override fun buildVisitor(
        holder: ProblemsHolder,
        isOnTheFly: Boolean
    ): PsiElementVisitor = UastVisitorAdapter(
        object : AbstractUastNonRecursiveVisitor() {
            override fun visitClass(node: UClass): Boolean {
                checkClass(node, holder)
                return true
            }
        }, true
    )

    private fun checkClass(uClass: UClass, holder: ProblemsHolder) {
        val psiClass = uClass.javaPsi

        if (psiClass.isInterface
            || psiClass.isEnum
            || psiClass.name == null
            || psiClass.hasModifierProperty(PsiModifier.ABSTRACT)) return

        if (!InheritanceUtil.isInheritor(psiClass, TARGET_INTERFACE)) return

        val fqn = psiClass.qualifiedName ?: return

        val registryFile = RegistryFileUtil.findRegistryFile(psiClass)

        if (registryFile != null && RegistryFileUtil.isRegistered(registryFile, fqn)) return

        val nameIdentifier = psiClass.nameIdentifier ?: return

        val resourcesRoot = if (registryFile == null) RegistryFileUtil.findResourcesRoot(psiClass) else null

        holder.registerProblem(
            nameIdentifier,
            "Plugin is not registered within $REGISTRATION_FILE",
            ProblemHighlightType.WARNING,
            PluginRegistrationWriter(fqn, registryFile, resourcesRoot)
        )
    }
}