package com.example.portafgliomedico;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragments extends Fragment {
    private Button doge;
    private Button ca_rossa;
    private Button centrale;
    private Button internazionale;
    private Button menis;
    private Button zamparo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragments newInstance(String param1, String param2) {
        SearchFragments fragment = new SearchFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        //First Button
        doge = getView().findViewById(R.id.doge);
        doge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=45.5040942,12.2529822&mode=1"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getActivity().getPackageManager())!= null)
                    startActivity(intent);
            }
        });
        //secondo bottone
        ca_rossa = getView().findViewById(R.id.ca_rossa);
        ca_rossa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=45.5012846,12.2520203&mode=1"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getActivity().getPackageManager())!= null)
                    startActivity(intent);
            }
        });
        //terzo bottone
        centrale = getView().findViewById(R.id.centrale);
        centrale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=45.4930196,12.2381314&mode=1"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getActivity().getPackageManager())!= null)
                    startActivity(intent);
            }
        });
        //quarto bottone
        internazionale = getView().findViewById(R.id.internazionale_salute);
        internazionale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=45.4926007,12.23759&mode=1"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getActivity().getPackageManager())!= null)
                    startActivity(intent);
            }
        });
        //quinto bottone
        menis = getView().findViewById(R.id.menis);
        menis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=45.4873016,12.2389229&mode=1"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getActivity().getPackageManager())!= null)
                    startActivity(intent);
            }
        });
        //sesto bottone
        zamparo = getView().findViewById(R.id.zamparo);
        zamparo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=45.486659,12.242328&mode=1"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getActivity().getPackageManager())!= null)
                    startActivity(intent);
            }
        });
    }
}