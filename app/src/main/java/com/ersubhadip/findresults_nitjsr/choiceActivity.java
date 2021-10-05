package com.ersubhadip.findresults_nitjsr;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;

public class choiceActivity extends AppCompatActivity {

    private AppCompatButton showResults;
    private EditText reg, name, sem;
    private ProgressBar progressBar;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    private LottieAnimationView noInternetLottie,animation;
    private TextView retry;
    private TextView title,sTitle,credit;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //force light theme,have to maintain for mi devices

        //initialisation
        showResults = findViewById(R.id.resultBtn);
        reg = findViewById(R.id.regEt);
        sem = findViewById(R.id.semEt);
        progressBar = findViewById(R.id.progressBar);
        name = findViewById(R.id.nameEt);
        retry=findViewById(R.id.retryTv);
        noInternetLottie=findViewById(R.id.internetIssueLottie);
        title=findViewById(R.id.title);
        sTitle=findViewById(R.id.subTitle);
        animation=findViewById(R.id.anim);
        credit=findViewById(R.id.creditTv);
        //end initialisation

        //todo: font updates

        //Checking Internet Connection
        connectivityManager=(ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null && networkInfo.isConnected()){
            reg.setVisibility(View.VISIBLE);
            sem.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            showResults.setVisibility(View.VISIBLE);
            sTitle.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            animation.setVisibility(View.VISIBLE);
            credit.setVisibility(View.VISIBLE);
            noInternetLottie.setVisibility(View.INVISIBLE);
            retry.setVisibility(View.INVISIBLE);
        }else{

            reg.setVisibility(View.INVISIBLE);
            sem.setVisibility(View.INVISIBLE);
            name.setVisibility(View.INVISIBLE);
            showResults.setVisibility(View.INVISIBLE);
            sTitle.setVisibility(View.INVISIBLE);
            title.setVisibility(View.INVISIBLE);
            animation.setVisibility(View.INVISIBLE);
            credit.setVisibility(View.INVISIBLE);
            noInternetLottie.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);


            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    networkInfo=connectivityManager.getActiveNetworkInfo();

                    if(networkInfo!=null && networkInfo.isConnected()){

                        reg.setVisibility(View.VISIBLE);
                        sem.setVisibility(View.VISIBLE);
                        name.setVisibility(View.VISIBLE);
                        showResults.setVisibility(View.VISIBLE);
                        sTitle.setVisibility(View.VISIBLE);
                        title.setVisibility(View.VISIBLE);
                        animation.setVisibility(View.VISIBLE);
                        credit.setVisibility(View.VISIBLE);
                        noInternetLottie.setVisibility(View.INVISIBLE);
                        retry.setVisibility(View.INVISIBLE);


                    }else{

                        Toast.makeText(choiceActivity.this, "Sorry, no internet!", Toast.LENGTH_SHORT).show();
                    }

                }
            });



        }


        //end

        //Text Watchers to EditTexts
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                checkValidUser();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                checkValidUser();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                checkValidUser();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //end of textWatchers
    }



//Functions to check the validity
    public  void checkValidUser(){
        //if else to check validity written above and implement in text watchers
            //Checking input validity

        showResults.setEnabled(false);


        if(!name.getText().toString().trim().equals("")){


            if(!reg.getText().toString().trim().equals("")){


                if(!sem.getText().toString().trim().equals("")){


                    if(reg.getText().toString().trim().substring(0,8).equals("2020UGEE")){

                        int roll=Integer.parseInt(reg.getText().toString().trim().substring(8));
                        if(roll>=1 && roll<=116){

                            if(sem.getText().toString().trim().length()==1 && Integer.parseInt(sem.getText().toString().trim())<3){
                                //needs changes during semester increment or in new semesters
                                showResults.setEnabled(true);
                                allowClicks();




                            }else{

                                //invalid semester error not text error
                                showResults.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        showResults.setEnabled(false);
                                        Intent error=new Intent(choiceActivity.this,SemesterErrorActivity.class);
                                        startActivity(error);

                                    }
                                });


                            }



                        }
                        else{

                            //if roll do not matches - invalid user
                            showResults.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    showResults.setEnabled(false);
                                    Intent error=new Intent(choiceActivity.this,SemesterErrorActivity.class);
                                    startActivity(error);

                                }
                            });


                        }



                    }else{

                            reg.setError("Please enter correct Registration Number!");
                            showResults.setEnabled(false);



                    }



                }else{

                    sem.setError("Please specify Semester Number!");
                }


            }else{

                reg.setError("Please enter the Registration Number!");

            }




        }else{

            name.setError("Please enter the Name!");

        }



        showResults.setEnabled(true);


        }
//Function to change activity after all checks passes
    public void allowClicks(){

        showResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showResults.setEnabled(false);
                dialog=new Dialog(choiceActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.loading_dialog);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                Toast.makeText(choiceActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                String nameData=name.getText().toString().trim();
                String regData=reg.getText().toString().trim();
                int  semData=Integer.parseInt(sem.getText().toString().trim());

                Intent it=new Intent(choiceActivity.this,displayResult.class);
                //passing some values to the next activity done here after all checks passes
                it.putExtra("Name",nameData);
                it.putExtra("Reg",regData);
                it.putExtra("Semester",semData);
                dialog.dismiss();
                startActivity(it);
                finish();
            }
        });



    }











    }

