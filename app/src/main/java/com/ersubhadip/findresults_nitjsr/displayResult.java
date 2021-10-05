package com.ersubhadip.findresults_nitjsr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.ersubhadip.findresults_nitjsr.Fragments.leaderBoardFragment;
import com.ersubhadip.findresults_nitjsr.Fragments.resultFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class displayResult extends AppCompatActivity {
    private FrameLayout parentFrame;
    BottomNavigationView bottomNavigationView;
    String nameDisplayData,regDisplayData;
    int semNumberDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //initialisation
        parentFrame=findViewById(R.id.mainFrame);
        bottomNavigationView=findViewById(R.id.bottomNav);
        //end






        //Fetching data from previous activity to tempo variable
        //we will pass it to result Fragment

        nameDisplayData = getIntent().getStringExtra("Name");
        regDisplayData = getIntent().getStringExtra("Reg");
        semNumberDisplay = getIntent().getIntExtra("Semester", 1);


        //end Fetching data from previous activity to tempo variable

        //setting up a bundle to send data from activity to Result Fragment
        Bundle bundle = new Bundle();
        bundle.putString("n",nameDisplayData);
        bundle.putString("r",regDisplayData);
        bundle.putInt("s",semNumberDisplay);
        resultFragment f= new resultFragment();
        f.setArguments(bundle);
        //end

        //setting up a bundle to send data from activity to Leader Fragment
        Bundle b=new Bundle();
        b.putString("RegForLeaderBoard",regDisplayData);
        b.putInt("SemLeaderBoard",semNumberDisplay);
        leaderBoardFragment l=new leaderBoardFragment();
        l.setArguments(b);
        //end

        //Setting default Frame
        defaultFragments(f);
        //end of setting default Frame


        //setting item listener to bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.res:
                        defaultFragments(f);
                        break;
                    case R.id.leader:


                        changeFragments(l);
                        break;

                }


                return false;
            }
        });
        //end of setting bottom navigation to the items
    }

    //Functions for Fragments Transactions
    private void defaultFragments(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);

        fragmentTransaction.replace(parentFrame.getId(),fragment);
        fragmentTransaction.commit();



    }
    private void changeFragments(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
        fragmentTransaction.replace(parentFrame.getId(),fragment);
        fragmentTransaction.commit();



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            //using default exit dialog
            AlertDialog.Builder builder=new AlertDialog.Builder(displayResult.this);
            builder.setTitle("Alert!");
            builder.setMessage("Do you want to exit this app?");
            builder.setCancelable(false);
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();



                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }
            });

            AlertDialog d=builder.create();
            d.show();
            //end exit dialog
        }else if(keyCode==KeyEvent.KEYCODE_HOME){
            finish();
        }

        return  false;
    }
}
































