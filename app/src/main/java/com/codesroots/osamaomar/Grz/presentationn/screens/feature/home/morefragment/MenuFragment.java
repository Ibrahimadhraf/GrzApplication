package com.codesroots.osamaomar.grz.presentationn.screens.feature.home.morefragment;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.entities.Currency;
import com.codesroots.osamaomar.grz.models.helper.PreferenceHelper;
import com.codesroots.osamaomar.grz.models.helper.ResourceUtil;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.ChatingActivity;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.chating.MessagesChatingActivity;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.contact.ContactFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainfragment.MainFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.productdetailsfragment.ProductDetailsModelFactory;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.login.LoginFragment;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.splash.SplashActivity;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.userlocations.UserLocationsFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;
    private TextView currency, lang, login, logout, chat, charge, direct_contact;
    private List<Currency.DataBean> dataBeanList = new ArrayList<>();

    private boolean curremtlang; ///true if arabic

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MenuViewModel.class);
        currency = view.findViewById(R.id.currency);
        login = view.findViewById(R.id.login);
        lang = view.findViewById(R.id.lang);
        logout = view.findViewById(R.id.logout);
        chat = view.findViewById(R.id.chat);
        charge = view.findViewById(R.id.charge);
        direct_contact = view.findViewById(R.id.direct_contact);
        chat.setOnClickListener(v ->
        {
            if (PreferenceHelper.getUserId() > 0)
                startActivity(new Intent(getContext(), MessagesChatingActivity.class));
            else
                Toast.makeText(getContext(), getText(R.string.loginfirst), Toast.LENGTH_SHORT).show();
        });

        currency.setOnClickListener(v -> mViewModel.getCurrencyData());
        login.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new LoginFragment()).addToBackStack(null).commit());
        direct_contact.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ContactFragment()).addToBackStack(null).commit());
        charge.setOnClickListener(v ->
                {
                    if (PreferenceHelper.getUserId() > 0)
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new UserLocationsFragment()).addToBackStack(null).commit();
                    else
                        Toast.makeText(getContext(), getText(R.string.loginfirst), Toast.LENGTH_SHORT).show();
                }
        );

        if (ResourceUtil.getCurrentLanguage(getActivity()).matches("en")) {
            login.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0);
            lang.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0);
            logout.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0);
            currency.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0);
            charge.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0);
            chat.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0);
        }

        if (PreferenceHelper.getUserId() > 0)
            login.setVisibility(View.GONE);
        else
            logout.setVisibility(View.GONE);

        logout.setOnClickListener(v -> {
            PreferenceHelper.setUserId(0);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MainFragment()).addToBackStack(null).commit();
            Toast.makeText(getActivity(), getText(R.string.youlogout), Toast.LENGTH_SHORT).show();
        });

        mViewModel.currencyMutableLiveData.observe(this, currency1 ->
        {
            dataBeanList = currency1.getData();
            showDialog(dataBeanList);
        });

//        if (ResourceUtil.getCurrentLanguage(getActivity()).matches("ar")) {
//            lang.setText("الانجليزية");
//            curremtlang = false;
//        } else {
//            lang.setText("Arabic");
//            curremtlang = true;
//        }

        lang.setOnClickListener(v -> {
            showDialogForLang();
        });
        return view;
    }

    private void showDialogForLang() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        builderSingle.setTitle(getText(R.string.choicelang));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("العربية");
        arrayAdapter.add("English");
        builderSingle.setNegativeButton(getText(R.string.cancel), (dialog, which) -> dialog.dismiss());
        builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
            String strName = arrayAdapter.getItem(which);
            AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
            builderInner.setMessage(strName);
            String lang = "";
            if (which == 0) {
                lang = "ar";
            } else if (which == 1) {
                lang = "en";
            }
            ResourceUtil.changeLang(lang, getActivity());
            Intent i = new Intent(getActivity(), SplashActivity.class);
            startActivity(i);
            getActivity().finishAffinity();
        });
        builderSingle.show();
    }

    private void showDialog(List<Currency.DataBean> dataBeanList) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        builderSingle.setTitle(getText(R.string.selectcurrency));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("OMR");
        arrayAdapter.add("USD");
        arrayAdapter.add("AED");
        arrayAdapter.add("SAR");

        builderSingle.setNegativeButton(getText(R.string.cancel), (dialog, which) -> dialog.dismiss());
        builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
            String strName = arrayAdapter.getItem(which);
            AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
            builderInner.setMessage(strName);
            if (which == 0) {
                PreferenceHelper.setCURRENCY(arrayAdapter.getItem(0));
                PreferenceHelper.setCURRENCY_VALUE(dataBeanList.get(0).getOmr());
            } else if (which == 1) {
                PreferenceHelper.setCURRENCY(arrayAdapter.getItem(1));
                PreferenceHelper.setCURRENCY_VALUE((float) dataBeanList.get(0).getUsd());
            } else if (which == 2) {
                PreferenceHelper.setCURRENCY(arrayAdapter.getItem(2));
                PreferenceHelper.setCURRENCY_VALUE((float) dataBeanList.get(0).getAed());
            } else if (which == 3) {
                PreferenceHelper.setCURRENCY(arrayAdapter.getItem(3));
                PreferenceHelper.setCURRENCY_VALUE((float) dataBeanList.get(0).getSar());
            }
//
//            builderInner.setTitle(getText(R.string.yourselect));
//            builderInner.setPositiveButton(getText(R.string.ok), (dialog1, which1) -> dialog1.dismiss());
//            builderInner.show();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MainFragment()).addToBackStack(null).commit();
        });
        builderSingle.show();
    }

    private ProductDetailsModelFactory getViewModelFactory() {
        return new ProductDetailsModelFactory(this.getActivity().getApplication());
    }

}
