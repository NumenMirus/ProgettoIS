package com.example.portafgliomedico;

import static android.content.ContentValues.TAG;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.portafgliomedico.ui.DBmanager;

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);

        final Button refreshButton = findViewById(R.id.refreshButton);
        final TextView listview = findViewById(R.id.listTextView);
        DBmanager db = new DBmanager(this);

        refreshButton.setOnClickListener(v -> {
            listview.setText("Testo reefreshed");
            Cursor res = db.getMemo();
            if(res.getCount()==0) {
                listview.setText("No memos!");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append("->  ").append(res.getString(0)).append(". Due date: ").append(res.getString(1)).append("\n");
            }
            listview.setText(buffer);
            Log.d(TAG, "onClick: refreshed 2");
        });

    }
}
