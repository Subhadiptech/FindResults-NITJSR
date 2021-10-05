package com.ersubhadip.findresults_nitjsr.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.findresults_nitjsr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class resultFragment extends Fragment {

    private TextView welcomeTv,name,regNumber,branch,semester,cgpa,sgpa;
    private FirebaseFirestore firestore;
    String regToShow;
    int semToShow;
    private ProgressBar cgpaBar,sgpaBar;
    private AppCompatButton download;

    public resultFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_result, container, false);

        //initialisation
        welcomeTv=view.findViewById(R.id.welcomeTv);
        name=view.findViewById(R.id.nameDispleyTv);
        regNumber=view.findViewById(R.id.regDisplayTv);
        semester=view.findViewById(R.id.semesterDisplayTv);
        branch=view.findViewById(R.id.branchDisplayTv);
        cgpa=view.findViewById(R.id.cgpaDisplayTv);
        sgpa=view.findViewById(R.id.sgpaDisplayTv);
        sgpaBar=view.findViewById(R.id.sgpaProgress);
        cgpaBar=view.findViewById(R.id.cgpaProgress);
        download=view.findViewById(R.id.downloadBtn);
        //end initialisation


        //check to see Bundle is Null or not
        if(getArguments()!=null){
            //Receive incoming data from activity
            String name=getArguments().getString("n");
            regToShow=getArguments().getString("r");
            semToShow=getArguments().getInt("s");


        }




        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialisation of Firebase Components
        firestore=FirebaseFirestore.getInstance();




        //Fetching Data From backend to Display Result According to semester

        firestore.collection("resultdata").document(regToShow).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {

               if(task.isSuccessful()){
                   DocumentSnapshot snap=task.getResult();
                    //Fetched the whole doc data inside snap
                   double sgpaData=(double)snap.get("sgpa"+ semToShow);
                   double cgpaData=(double)snap.get("cgpa"+ semToShow);

                   //Setting the fetched data
                   name.setText((String)snap.get("name"));
                   branch.setText((String)snap.get("branch"));
                   welcomeTv.setText("Welcome, "+(((String) snap.get("name")).substring(0,((String) snap.get("name")).indexOf(' '))));
                   regNumber.setText((String)snap.get("reg"));
                   semester.setText(""+semToShow);
                   sgpa.setText(""+sgpaData);
                   cgpa.setText(""+cgpaData);
                    //end

                    //setting ProgressBar Level
                    sgpaBar.setMax(10);
                    cgpaBar.setMax(10);
                    sgpaBar.setProgress((int)sgpaData);
                    cgpaBar.setProgress((int)cgpaData);

                    //end



                }else{

                    Toast.makeText(getContext(), "Something Went Wrong!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

               }

            }
        });



        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo:Result pdf Download Functionality
                Toast.makeText(getContext(), "Under Development", Toast.LENGTH_SHORT).show();
            }
        });




    }



}