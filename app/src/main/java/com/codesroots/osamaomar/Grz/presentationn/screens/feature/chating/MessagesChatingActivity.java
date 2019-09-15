package com.codesroots.osamaomar.grz.presentationn.screens.feature.chating;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.databinding.ActivityChatBinding;
import com.codesroots.osamaomar.grz.models.entities.ChatList;
import com.codesroots.osamaomar.grz.models.entities.chatmessages;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.adapters.ChatListAdapter;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.MainViewModelFactory;
import java.util.ArrayList;
import java.util.List;

public class MessagesChatingActivity extends AppCompatActivity {

    ChatListAdapter chatListAdapter;
    private List<chatmessages.DataBean> allMessage;
    EditText etMessage;
    private static final int LOAD_IMG_REQUEST_CODE = 123;
    ImageView send, getimage, ivNotFound;
    ChatViewModel chatViewModel;
    RecyclerView recyclerView;
    ActivityChatBinding chatBinding;
    List<ChatList.DataBean> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        chatViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ChatViewModel.class);
        chatViewModel.getChatData(PreferenceHelper.getUserId());
        chatViewModel.chatListMutableLiveData.observe(this, chatList ->
        {
            messages = chatList.getData();
            chatBinding.progress.setVisibility(View.GONE);
            chatListAdapter = new ChatListAdapter(this,  messages);
            chatBinding.setMessagesadapter(chatListAdapter);
            chatBinding.recylerview.setAdapter(chatListAdapter);
            chatBinding.recylerview.scrollToPosition(chatListAdapter.getItemCount() - 1);
        });

        chatViewModel.error.observe(this, throwable ->
        {
            chatBinding.progress.setVisibility(View.GONE);
          //  Toast.makeText(this, getText(R.string.erroroccure), Toast.LENGTH_SHORT).show();
        });

        chatViewModel.addmessageMutableLiveData.observe(this,addmessage ->
        { messages.add(new ChatList.DataBean(addmessage.getChatting().getSender(),addmessage.getChatting().getMessage_text(),""));
            chatListAdapter.notifyDataSetChanged();
            chatBinding.etMessage.setText("");
        });

        chatBinding.ivSend.setOnClickListener(v -> {
       //     if (PreferenceHelper.getUserId() > 0) {
                if (chatBinding.etMessage.getText().toString().matches(""))
                    chatBinding.etMessage.setError(getText(R.string.empty));
                else
                    chatViewModel.addmessge(PreferenceHelper.getUserId(), chatBinding.etMessage.getText().toString());
         //   }
        });
    }

    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(getApplication());
    }

}
