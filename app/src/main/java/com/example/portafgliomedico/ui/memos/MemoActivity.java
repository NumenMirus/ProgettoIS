package com.example.portafgliomedico.ui.memos;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.portafgliomedico.MainActivity;
import com.example.portafgliomedico.R;
import com.example.portafgliomedico.ui.DBmanager;

public class MemoActivity extends AppCompatActivity {
     @Override
    protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.fragment_memos);
         Log.d(TAG, "onCreate: Created");

         final EditText text = findViewById(R.id.nomeMedicale);
         final EditText due_date = findViewById(R.id.dataTime);
         final Button addButton = findViewById(R.id.addToDatabaseButton);
         final Button backButton = findViewById(R.id.backButton);
         final TextView textView = findViewById(R.id.text_slideshow);
         final DBmanager db = new DBmanager(this);

         addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String memoText = String.valueOf(text.getText());
                 String memoDueDate = String.valueOf(due_date.getText());
                 Boolean checkInsertData = db.insertMemo(memoText, memoDueDate);
                 if(checkInsertData) {
                     textView.setText("Memo added to database!");
                     text.setText("");
                     due_date.setText("");
                 }
                 else {
                     textView.setText("An error occurred, try again");
                 }
             }
         });


         backButton.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MemoActivity.this, MainActivity.class);
                 startActivity(i);
             }
         });
     }
}
