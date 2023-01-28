package com.example.portafgliomedico;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.portafgliomedico.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    //number of select tab. We have 4 tabs so value must lie between 1-4 default value is 1
    private int selectedTab = 1;
    private String text_welcome;
    private Map<String, Object> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if(prefs.getBoolean("firstTime", true)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this,R.style.blue_text);

            alert.setTitle("Registrazione");
            alert.setMessage("Inserisci nome e cognome");

            // Set an EditText view to get user input
            final EditText input = new EditText(this);
            input.setHint("Nome");
            final EditText input2 = new EditText(this);
            input2.setHint("Cognome");
            // Set a layout to use 2 different input in AlertDialog
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(input);
            layout.addView(input2);
            alert.setView(layout);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @SuppressLint("SetTextI18n")
                public void onClick(DialogInterface dialog, int whichButton) {
                    String value = String.valueOf(input.getText());
                    TextView welcome = findViewById(R.id.welcome_string_home);
                    text_welcome = "Bentornato, "+value;
                    Log.d(TAG, "onClick: "+text_welcome);
                    welcome.setText(text_welcome);
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();
            SharedPreferences.Editor editor = prefs.edit();
            user.put("Nome", "Filippo");
            user.put("Cognome", "Tiozzo");
            db.collection("utenti")
                    .add(user);
            editor.putBoolean("firstTime", false);
            editor.commit();
        }

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout searchLayout = findViewById(R.id.searchLayout);
        final LinearLayout notificationLayout = findViewById(R.id.notificationLayout);
        final LinearLayout accountLayout = findViewById(R.id.accountLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView searchImage = findViewById(R.id.searchImage);
        final ImageView notificationImage = findViewById(R.id.notificationImage);
        final ImageView accountImage = findViewById(R.id.accountImage);

        final TextView homeTxt = findViewById(R.id.homeTxt);
        final TextView searchTxt = findViewById(R.id.searchTxt);
        final TextView notificationTxt = findViewById(R.id.notificationTxt);
        final TextView accountTxt = findViewById(R.id.accountTxt);


        //set home fragment by default
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, Home1Fragment.class,null)
                .commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if home is already selected or not
                if(selectedTab != 1){

                    //set home fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, Home1Fragment.class,null)
                            .commit();

                    //unselect other tabs expect home tab
                    searchTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);
                    accountTxt.setVisibility(View.GONE);


                    searchImage.setImageResource(R.drawable.pharmacy_icon);
                    notificationImage.setImageResource(R.drawable.notification_icon_nav_bar);
                    accountImage.setImageResource(R.drawable.account_icon_navbar);

                    searchLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    accountLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home_selected_icon);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home_100);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    //set 1st tab as selected tab
                    selectedTab = 1;

                }

            }
        });

        searchLayout.setOnClickListener(view -> {

            //check if search is already selected or not
            if(selectedTab != 2){

                //set search fragment
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, SearchFragments.class,null)
                        .commit();

                //unselect other tabs expect home tab
                homeTxt.setVisibility(View.GONE);
                notificationTxt.setVisibility(View.GONE);
                accountTxt.setVisibility(View.GONE);


                homeImage.setImageResource(R.drawable.home_selected_icon);
                notificationImage.setImageResource(R.drawable.notification_icon_nav_bar);
                accountImage.setImageResource(R.drawable.account_icon_navbar);

                homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                accountLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                //select search tab
                searchTxt.setVisibility(View.VISIBLE);
                searchImage.setImageResource(R.drawable.pharmacy_icon);
                searchLayout.setBackgroundResource(R.drawable.round_back_search_100);

                //create animation
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                searchLayout.startAnimation(scaleAnimation);

                //set 2st tab as selected tab
                selectedTab = 2;
            }

        });


        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if notification is already selected or not
                if(selectedTab != 3){

                    // set notification fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, NotificationFragment.class,null)
                            .commit();

                    //unselect other tabs expect notification tab
                    homeTxt.setVisibility(View.GONE);
                    searchTxt.setVisibility(View.GONE);
                    accountTxt.setVisibility(View.GONE);


                    homeImage.setImageResource(R.drawable.home_icon_nav_bar);
                    searchImage.setImageResource(R.drawable.pharmacy_icon);
                    accountImage.setImageResource(R.drawable.account_icon_navbar);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    searchLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    accountLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    notificationTxt.setVisibility(View.VISIBLE);
                    notificationImage.setImageResource(R.drawable.notification_selected_icon);
                    notificationLayout.setBackgroundResource(R.drawable.round_back_notification_100);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    notificationLayout.startAnimation(scaleAnimation);

                    //set 3rd tab as selected tab
                    selectedTab = 3;
                }

            }
        });

        accountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if account is already selected or not
                if(selectedTab != 4){

                    //set account fragment
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, AccountFragment.class,null)
                            .commit();

                    //unselect other tabs expect account tab
                    homeTxt.setVisibility(View.GONE);
                    searchTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);


                    homeImage.setImageResource(R.drawable.home_icon_nav_bar);
                    searchImage.setImageResource(R.drawable.pharmacy_icon);
                    notificationImage.setImageResource(R.drawable.notification_icon_nav_bar);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    searchLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    accountTxt.setVisibility(View.VISIBLE);
                    accountImage.setImageResource(R.drawable.account_selected_icon);
                    accountLayout.setBackgroundResource(R.drawable.round_back_account_100);

                    //create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    accountLayout.startAnimation(scaleAnimation);

                    //set 4th tab as selected tab
                    selectedTab = 4;
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}