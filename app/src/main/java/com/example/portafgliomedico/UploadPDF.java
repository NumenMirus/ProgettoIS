package com.example.portafgliomedico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.ref.Reference;

public class UploadPDF extends AppCompatActivity {

    Button upload_btn;
    EditText pdf_name;

    //Database
    FirebaseStorage storage = FirebaseStorage.getInstance("gs://portafogliomedico-a055e.appspot.com/");
    StorageReference storageRef = storage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        upload_btn=findViewById(R.id.upload_btn);
        pdf_name=findViewById(R.id.name);

        upload_btn.setOnClickListener(view -> selectFiles());
    }

    private void selectFiles() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF Files..."),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            UploadFiles(data.getData());
        }
    }

    private void UploadFiles(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog((this));
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageRef.child("/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(taskSnapshot -> {

            Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
            while(!uriTask.isComplete()){
                Toast.makeText(UploadPDF.this, "File Uploaded!", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }

        }).addOnProgressListener(snapshot -> {
            double progress = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
            progressDialog.setMessage("Uploaded:"+(int)progress+"%");
        });
    }
}