package com.saver12.plugins

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class TelegramBuildNotifierTask extends DefaultTask {

    @Input
    String tgToken

    @Input
    String chatId

    TelegramBuildNotifierTask() {
        description = 'Notifies about build start to your Telegram Bot'
        group = 'Telegram'
    }

    @TaskAction
    void start() {
        ApiContextInitializer.init()

        if (tgToken == null || chatId == null) {
            return
        }

        TelegramLongPollingBot bot = new TelegramLongPollingBot() {
            @Override
            String getBotToken() {
                return tgToken
            }

            @Override
            void onUpdateReceived(Update update) {
            }

            @Override
            String getBotUsername() {
                return "botName"
            }
        }

        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText('Build started!')

        try {
            bot.execute(message)
        } catch (Exception ex) {
            logger.quiet "You have to specify correct 'botToken' and 'chatId' values in gradle.properties"
        }
    }
}
