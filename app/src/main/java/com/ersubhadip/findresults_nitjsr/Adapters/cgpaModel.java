package com.ersubhadip.findresults_nitjsr.Adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class cgpaModel {

    String rank,leaderName,registration;



    public cgpaModel(String rank, String leaderName, String registration) {
        this.rank = rank;
        this.leaderName = leaderName;
        this.registration = registration;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
        }
    }
}

