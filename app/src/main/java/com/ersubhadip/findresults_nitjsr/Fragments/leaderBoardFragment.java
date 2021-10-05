package com.ersubhadip.findresults_nitjsr.Fragments;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.ersubhadip.findresults_nitjsr.Adapters.adapter;
import com.ersubhadip.findresults_nitjsr.Adapters.cgpaModel;
import com.ersubhadip.findresults_nitjsr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class leaderBoardFragment extends Fragment {

    private RecyclerView rv;
    private adapter ad;
    ArrayList<cgpaModel> list=new ArrayList<>();
    private FirebaseFirestore fb;
    String regAgain;
    int semAgain;
    private Dialog d;


    public leaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leader_board, container, false);
        //initialisation
        rv=view.findViewById(R.id.leaderRv);

        if(getArguments()!=null){
            //Receive incoming data from activity
            regAgain=getArguments().getString("RegForLeaderBoard");
            semAgain=getArguments().getInt("SemLeaderBoard");

        }


        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //statements
        fb=FirebaseFirestore.getInstance();

        //dialog starts before server process begins
        //Dialog
        d=new Dialog(getContext());
        d.setCancelable(false);
        d.setContentView(R.layout.loading_dialog);
        d.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.bg));
        d.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        d.show();



        //adding names to the leaderBoard with loop from fireStore
        fb.collection("resultdata").orderBy("sgpa"+semAgain).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                list.clear();

                if(task.isSuccessful()){
                    int i=1;

                    for(DocumentSnapshot s: Objects.requireNonNull(task.getResult())){

                        list.add(new cgpaModel(""+i,(String)s.get("name"),(String)s.get("reg")));
                        i++;

                    }


                }else{

                    Toast.makeText(getContext(), "Something Went Wrong!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
        //end



        //layoutManager for layout in adapter
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(manager);
        //end


        //dialog close
        d.dismiss();


        //initialise adapter and using it
        ad=new adapter(list);
        rv.setAdapter(ad);
        ad.notifyDataSetChanged();
        //end

    }

}