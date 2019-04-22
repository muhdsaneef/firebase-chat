package com.dailyapps.scribbles.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.dailyapps.scribbles.app.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatModel implements Parcelable {
    public static final Creator<ChatModel> CREATOR = new Creator<ChatModel>() {
        @Override
        public ChatModel createFromParcel(Parcel in) {
            return new ChatModel(in);
        }

        @Override
        public ChatModel[] newArray(int size) {
            return new ChatModel[size];
        }
    };

    private int chatType;

    private String chatText;

    private long chatTimeStamp;

    private String chatDate;


    public ChatModel() {
    }

    private ChatModel(Parcel in) {
        chatType = in.readInt();
        chatText = in.readString();
        chatTimeStamp = in.readLong();
        chatDate = in.readString();
    }

    public int getChatType() {
        return chatType;
    }

    public long getChatTimeStamp() {
        return chatTimeStamp;
    }

    public String getTimeStampKey() {
        return Long.toString(getChatTimeStamp());
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public void setChatTimeStamp(long chatTimeStamp) {
        this.chatTimeStamp = chatTimeStamp;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    public String getChatText() {
        return chatText;
    }

    public String getChatDate() {
        return chatDate;
    }

    public String getChatTime() {
        Date date = new Date(getChatTimeStamp());
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a", Locale.getDefault());
        return formatter.format(date);
    }

    @Override
    public int describeContents() {
        return AppConstants.ZERO;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(chatType);
        parcel.writeString(chatText);
        parcel.writeLong(chatTimeStamp);
        parcel.writeString(chatDate);
    }
}
