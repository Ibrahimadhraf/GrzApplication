package com.codesroots.osamaomar.grz.presentationn.screens.feature.contact;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.helper.ResourceUtil;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.MainViewModelFactory;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.userlocations.UserLocationsViewModel;

public class ContactFragment extends Fragment {

    TextView phone1,phone2,mail;
    ImageView whats,insta;
    ContactViewModel  contactViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment, container, false);

        phone2 = view.findViewById(R.id.phone2);
        mail = view.findViewById(R.id.mail);
        insta = view.findViewById(R.id.insta);
        whats = view.findViewById(R.id.whats);

        whats.setOnClickListener(v -> ResourceUtil.openWhatsApp(phone2.getText().toString(),getContext()));
        phone2.setOnClickListener(v -> ResourceUtil.callNumber(phone2.getText().toString(),getContext()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(ContactViewModel.class);
        contactViewModel.contactMutableLiveData.observe(this,contact ->
        {
            phone2.setText(contact.getData().get(0).getContact_phone());
            mail.setText(contact.getData().get(0).getContact_email());
        } );

        contactViewModel.error.observe(this,throwable ->
                Toast.makeText(getActivity(),getText(R.string.erroroccure),Toast.LENGTH_SHORT).show()
        );
    }

    private MainViewModelFactory getViewModelFactory() {
        return new MainViewModelFactory(getActivity().getApplication());
    }


}
