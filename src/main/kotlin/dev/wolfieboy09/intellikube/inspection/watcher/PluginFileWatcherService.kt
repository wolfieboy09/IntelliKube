package dev.wolfieboy09.intellikube.inspection.watcher

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.AsyncFileListener
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.events.VFileContentChangeEvent
import dev.wolfieboy09.intellikube.inspection.PluginRegistrationInspection

@Service(Service.Level.PROJECT)
class PluginFileWatcherService(project: Project) : Disposable {
    init {
        VirtualFileManager.getInstance().addAsyncFileListener(
            AsyncFileListener { events ->
                val relevant = events.filterIsInstance<VFileContentChangeEvent>()
                    .any { it.file.name == PluginRegistrationInspection.REGISTRATION_FILE }

                if (!relevant) return@AsyncFileListener null

                object : AsyncFileListener.ChangeApplier {
                    override fun afterVfsChange() {
                        DaemonCodeAnalyzer.getInstance(project).settingsChanged()
                    }
                }
            },
            this
        )
    }

    override fun dispose() {}
}