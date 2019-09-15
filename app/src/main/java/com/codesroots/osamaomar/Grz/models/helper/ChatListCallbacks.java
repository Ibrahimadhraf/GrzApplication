package com.codesroots.osamaomar.grz.models.helper;


import com.codesroots.osamaomar.grz.models.entities.ChatList;

public interface ChatListCallbacks {

    public void onItemClick(ChatList.DataBean item);
    public void onCallItem(String  phone);

}
