package com.saver12.plugins

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

open class TelegramBuildNotifierTask : DefaultTask() {

    @Input
    var tgToken: String? = null

    @Input
    var chatId: String? = null

    @TaskAction
    fun start() {
        ApiContextInitializer.init()

        val bot = object : TelegramLongPollingBot() {
            override fun getBotUsername(): String = "botName"
            override fun getBotToken(): String? = tgToken
            override fun onUpdateReceived(update: Update?) {}
        }

        val message = SendMessage()
                .setChatId(chatId)
                .setText("Build started!")

        try {
            bot.execute(message)
        } catch (e: Exception) {
            logger.quiet("You have to specify correct 'botToken' and 'chatId' values in gradle.properties")
        }
    }
}
