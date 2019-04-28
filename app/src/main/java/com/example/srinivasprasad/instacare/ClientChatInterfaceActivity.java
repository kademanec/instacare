package com.example.srinivasprasad.instacare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ClientChatInterfaceActivity extends AppCompatActivity {

    private CardView groupChat,doctorChat;
    private Toolbar chatBarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_chat_interface);

        chatBarMain=findViewById(R.id.chat_bar_main);
        setSupportActionBar(chatBarMain);
        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        groupChat = findViewById(R.id.group_chat_card);
        doctorChat=findViewById(R.id.doctor_chat_card);

        groupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClientChatInterfaceActivity.this,ChatActivity.class);
                startActivity(i);
            }
        });

        doctorChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClientChatInterfaceActivity.this,PrivateChatActivity.class);
                startActivity(i);
            }
        });

    }
}
