package dev.wolfieboy09.inspection.watcher

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class PluginFileWatcher : ProjectActivity {
    override suspend fun execute(project: Project) {
        project.service<PluginFileWatcherService>()
    }
}