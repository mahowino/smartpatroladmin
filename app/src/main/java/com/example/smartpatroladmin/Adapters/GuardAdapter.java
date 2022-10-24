package com.example.smartpatroladmin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GuardAdapter extends RecyclerView.Adapter<GuardAdapter.ViewHolder> {
    Context mContext;
    List<Guard> guards;

    public GuardAdapter(Context mContext, List<Guard> guards) {
        this.mContext = mContext;
        this.guards = guards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.guard_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setTexts(guards.get(position),holder);
    }

    private void setTexts(Guard guard, ViewHolder holder) {
        holder.guardName.setText(guard.getGuardName());
        //implement picasso library or Glide library to be able to load guard profile picture easily
        Picasso.with(mContext)
                .load(guard.getGuardProfilePicture())
                .into(holder.guardProfilePicture);
    }

    @Override
    public int getItemCount() {
        return guards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView guardProfilePicture;
        TextView guardName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guardName=itemView.findViewById(R.id.txtGuardName);
            guardProfilePicture=itemView.findViewById(R.id.imgGuardProfilePic);

        }
    }
}
