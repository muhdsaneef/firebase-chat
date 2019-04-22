package com.dailyapps.scribbles.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.dailyapps.scribbles.BuildConfig;
import com.dailyapps.scribbles.R;
import com.dailyapps.scribbles.adapters.ChatsAdapter;
import com.dailyapps.scribbles.app.AppConstants;
import com.dailyapps.scribbles.models.ChatModel;
import com.dailyapps.scribbles.utils.AppUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {


    @BindView(R.id.edt_chat_text)
    EditText edtChatField;

    @BindView(R.id.rv_chats)
    RecyclerView rvChats;

    private DatabaseReference myRef;
    private List<ChatModel> chatList;
    private ChatsAdapter chatsAdapter;
    private String chatRoomName;

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            ChatModel chat = dataSnapshot.getValue(ChatModel.class);
            chatList.add(chat);
            chatsAdapter.notifyDataSetChanged();
            rvChats.scrollToPosition(chatList.size() - 1);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        chatList = new ArrayList<>();
        fetchChatRoomName();
        initFireBaseReferences();
        setChatList();
        fetchChatHistory();
    }

    private void fetchChatRoomName() {
        chatRoomName = getIntent().getStringExtra(AppConstants.CHAT_ROOM_NAME);
        chatRoomName += BuildConfig.CHAT_ROOM_ID;
    }

    private void fetchChatHistory() {
        myRef.child(AppConstants.CHATS_PATH).child(AppUtils.getFormattedTitle(chatRoomName)).child(getCurrentDate()).addChildEventListener(childEventListener);
    }

    private void setChatList() {
        chatsAdapter = new ChatsAdapter(chatList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvChats.setLayoutManager(layoutManager);
        rvChats.setAdapter(chatsAdapter);
    }

    @OnClick(R.id.img_send)
    public void onSendChatClicked(View view) {
        if(validateMessage()) {
            sendChat(createChatObject());
            clearChatField();
        }
    }

    private void clearChatField() {
        edtChatField.setText("");
    }

    private boolean validateMessage() {
        return !edtChatField.getText().toString().trim().isEmpty();
    }

    private ChatModel createChatObject() {
        ChatModel chat = new ChatModel();
        chat.setChatText(edtChatField.getText().toString().trim());
        chat.setChatTimeStamp(System.currentTimeMillis());
        chat.setChatType(BuildConfig.CHAT_CONSTANT);
        chat.setChatDate(getCurrentDate());
        return chat;
    }

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat(AppConstants.DATE_FORMAT, Locale.getDefault());
        return dateFormatter.format(calendar.getTime());
    }

    private void initFireBaseReferences() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    private void sendChat(ChatModel chatObject) {
        myRef.child(AppConstants.CHATS_PATH).child(AppUtils.getFormattedTitle(chatRoomName)).child(chatObject.getChatDate()).child(chatObject.getTimeStampKey()).setValue(chatObject);
    }
}
