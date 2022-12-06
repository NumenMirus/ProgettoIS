package com.example.portafgliomedico.ui.memos;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.portafgliomedico.databinding.FragmentMemosBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MemosFragment extends Fragment {

    private FragmentMemosBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MemosViewModel slideshowViewModel =
                new ViewModelProvider(this).get(MemosViewModel.class);

        binding = FragmentMemosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.addToDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data from Editfields
                String nome = new String(binding.editTextName.getText().toString());
                Boolean aperto = new Boolean(binding.switchAperto.isChecked());
                Log.d(TAG, "nomeValue.isEmpty(): "+ nome.isEmpty());
                Log.d(TAG, "apertoValue: "+ aperto);

                if(!nome.isEmpty()){
                    // Creates a map object to be stored in the database
                    Map<String, Object> pharmacy = new HashMap<>();

                    // Populate map fields
                    pharmacy.put("aperto", aperto);
                    pharmacy.put("nome", nome);
                    Log.d(TAG, "onClick: Created Map Object");

                    db.collection("one").document()
                            .set(pharmacy)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                    binding.textSlideshow.setText("DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                    binding.textSlideshow.setText("Error writing document");
                                }
                    });
                    
                    binding.editTextName.setText("");
                    binding.switchAperto.setChecked(false);
                    
                }else{
                    Log.d(TAG, "onClick: Empty fields, nothing to push to database");
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}