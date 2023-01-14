package com.example.portafgliomedico;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.portafgliomedico.ui.memos.MemoActivity;
import com.example.portafgliomedico.ui.memos.MemosFragment;
import com.example.portafgliomedico.ui.vault.VaultFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home1Fragment.
     */


    // TODO: Rename and change types and number of parameters
    public static Home1Fragment newInstance(String param1, String param2) {
        Home1Fragment fragment = new Home1Fragment();
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

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton btnMemo = (ImageButton)view.findViewById(R.id.widget_memo);
        ImageButton btnSearch = (ImageButton)view.findViewById(R.id.widget_search);
        ImageButton btnVault = (ImageButton)view.findViewById(R.id.widget_vault);
        //ImageButton btnOrderMed = (ImageButton)view.findViewById(R.id.widget_order);


        btnMemo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), MemoActivity.class);
                startActivity(i);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new SearchFragments());
                fr.commit();
            }
        });

        btnVault.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragmentContainer,new VaultFragment());
                fr.commit();
            }
        });

        return view;
    }
}