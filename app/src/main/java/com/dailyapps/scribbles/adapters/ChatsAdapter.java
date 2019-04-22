package com.dailyapps.scribbles.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dailyapps.scribbles.BuildConfig;
import com.dailyapps.scribbles.R;
import com.dailyapps.scribbles.app.AppConstants;
import com.dailyapps.scribbles.models.ChatModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private List<ChatModel> listOfChats;
    private Context context;

    public ChatsAdapter(List<ChatModel> listOfChats, Context context) {
        this.listOfChats = listOfChats;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return listOfChats.get(position).getChatType();
    }

    @NonNull
    @Override
    public ChatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_chat_right, viewGroup, false);
        if(viewType != BuildConfig.CHAT_CONSTANT) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_chat_left, viewGroup, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.tvChatText.setText(listOfChats.get(position).getChatText());
        viewHolder.tvChatTime.setText(listOfChats.get(position).getChatTime());
    }

    @Override
    public int getItemCount() {
        return listOfChats != null ? listOfChats.size() : AppConstants.ZERO;
    }

    public void setNewChatList(List<ChatModel> chatList) {
        this.listOfChats = chatList;
        notifyDataSetChanged();
    }

    class ViewHolder  extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_chat_text)
        TextView tvChatText;

        @BindView(R.id.tv_chat_time)
        TextView tvChatTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
