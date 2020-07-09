package com.orca.marketplace;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot{

    static private String info;

    Bot(String info){Bot.info= info;}

    String apiToken = "1218292401:AAGRv6BQV3pq4eHetdqq_W56vzLWUlKM0rg";

    @Override
    public void onUpdateReceived(Update update) {

            // Set variables
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(info);
        try{
            execute(message);
        }catch(Exception ignored){}
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "domaditos de Dios";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return apiToken;
    }}
