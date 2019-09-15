package com.codesroots.osamaomar.grz.presentationn.screens.feature.confirmorder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.myorders.MyOrdersFragment;

public class FinishOrderFragment extends Fragment {
    public FinishOrderFragment() {
        // Required empty public constructor
    }

    TextView gotodelivery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.first_finish_order, container, false);
        gotodelivery = view.findViewById(R.id.gotodelivery);
        gotodelivery.setOnClickListener(v ->
         getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MyOrdersFragment()).addToBackStack(null).commit()
        );
        return view;
    }


}
