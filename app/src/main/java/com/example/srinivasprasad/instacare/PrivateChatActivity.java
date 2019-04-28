package com.example.srinivasprasad.instacare;

import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrivateChatActivity extends AppCompatActivity {

    private Toolbar pChatToolBar;
    private ImageView pSendBtn;

    private EditText pMsgBox;

    private String current_user_id;

    private MediaPlayer mediaPlayer;
    private Boolean sound=true;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);

        pChatToolBar = findViewById(R.id.doct_chat_toolbar);
        setSupportActionBar(pChatToolBar);
        getSupportActionBar().setTitle("Doctor Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);


        pSendBtn=findViewById(R.id.pMsgSendButton);
        pMsgBox=findViewById(R.id.pMsg_box);




        /////////Messege adding
        pSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sound=false;
                mediaPlayer = MediaPlayer.create(PrivateChatActivity.this, R.raw.message_sent);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.release();
                    }
                });
                String msg = pMsgBox.getText().toString();

                if (!TextUtils.isEmpty(msg)){

                    Map<String,Object> chatMap = new HashMap<>();
                    chatMap.put("messege",msg);
                    chatMap.put("user_id",current_user_id);
                    chatMap.put("timestamp", FieldValue.serverTimestamp());

                    firebaseFirestore.collection("DoctorChat/"+current_user_id+"/chats").add(chatMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isSuccessful()){
                                pMsgBox.setText(null);
                                Toast.makeText(PrivateChatActivity.this, "Messege sent Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PrivateChatActivity.this, ""+task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        current_user_id=firebaseAuth.getUid();
    }
}
