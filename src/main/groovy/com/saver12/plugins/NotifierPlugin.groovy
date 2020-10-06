package com.saver12.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class NotifierPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply(JavaPlugin)
        addAppTasks(project)
    }

    private static void addAppTasks(Project project) {
        def notifyTg = project.task('notifyTg', type: TelegramBuildNotifierTask) {
            tgToken = project.hasProperty('botToken') ? project.botToken : ''
            chatId = project.hasProperty('chatId') ? project.chatId : ''
        }
        project.tasks.find { it.name == 'build' }.dependsOn notifyTg
    }
}
