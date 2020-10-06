package com.saver12.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register

class NotifierPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply(JavaPlugin::class.java)
        addAppTasks(target)
    }

    private fun addAppTasks(project: Project) {
        with(project) {
            tasks.register<TelegramBuildNotifierTask>("notifyTg") {
                val botToken: String? by project
                val chatNum: String? by project
                tgToken = if (botToken != null) botToken else ""
                chatId = if (chatNum != null) chatNum else ""
                group = "Telegram"
                description = "Notifies about build start to your Telegram Bot"
            }

            tasks.find { it.name == "build" }?.dependsOn("notifyTg")
        }
    }
}
