package com.dailyapps.scribbles.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dailyapps.scribbles.R;
import com.dailyapps.scribbles.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatEntryScreen extends BaseActivity {

    @BindView(R.id.edt_chat_room)
    EditText edtChatRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_entry_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_enter_chat)
    public void onEnterClicked(View view) {
        if(AppUtils.validateChatRoomName(edtChatRoom.getText().toString())) {
            showScreen(ChatActivity.class, edtChatRoom.getText().toString().trim());
        }
    }
}
