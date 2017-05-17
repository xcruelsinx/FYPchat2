package com.example.a15055088.fypchat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private LinearLayout singleMessageContainer;
    LinearLayout parent_layout ;

    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chat_entry, parent, false);
        }
        singleMessageContainer = (LinearLayout) row.findViewById(R.id.chatBubble);
        parent_layout = (LinearLayout) row.findViewById(R.id.bubble_layout_parent);
        ChatMessage chatMessageObj = getItem(position);
        chatText = (TextView) row.findViewById(R.id.message_text);
        chatText.setText(chatMessageObj.message);
        //chatText.setBackgroundResource(chatMessageObj.isMine ? R.drawable.bubble_me : R.drawable.bubble);
        //singleMessageContainer.setGravity(chatMessageObj.isMine ? Gravity.RIGHT : Gravity.LEFT);
        // if message is mine then align to right
        if (chatMessageObj.isMine) {
            chatText.setBackgroundResource(R.drawable.bubble_me);
            parent_layout.setGravity(Gravity.RIGHT);
        }
        // If not mine then align to left
        else {
            chatText.setBackgroundResource(R.drawable.bubble);
            parent_layout.setGravity(Gravity.LEFT);
        }
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}