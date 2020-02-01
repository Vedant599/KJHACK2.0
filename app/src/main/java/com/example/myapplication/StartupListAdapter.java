package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StartupListAdapter extends RecyclerView.Adapter<StartupListAdapter.Holder> {
    Context context;
    StartupData startupData;
    public StartupListAdapter(Context context,StartupData startupData) {
        super();
        this.context=context;
        this.startupData=startupData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.startup_list_item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
