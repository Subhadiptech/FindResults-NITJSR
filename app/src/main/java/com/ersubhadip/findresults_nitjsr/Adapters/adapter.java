package com.ersubhadip.findresults_nitjsr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ersubhadip.findresults_nitjsr.R;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private ArrayList<cgpaModel> listAdapter=new ArrayList<>();


    public adapter(ArrayList<cgpaModel> listAdapter) {
        this.listAdapter = listAdapter;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_rv, parent, false);




        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {

        holder.setData(listAdapter.get(position).rank,
                listAdapter.get(position).leaderName,
                listAdapter.get(position).registration);



    }

    @Override
    public int getItemCount() {
        return listAdapter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView rankAdapter,nameAdapter,regAdapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rankAdapter=itemView.findViewById(R.id.rankTv);
            nameAdapter=itemView.findViewById(R.id.leaderNameTv);
            regAdapter=itemView.findViewById(R.id.registrationTv);

        }

        public void setData(String r,String n,String registration){

            rankAdapter.setText(r);
            nameAdapter.setText(n);
            regAdapter.setText(registration);


        }
    }
}
