package com.example.smartpatroladmin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatroladmin.IncidentActivity;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.PastPatrolsActivity;
import com.example.smartpatroladmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GuardAdapter extends RecyclerView.Adapter<GuardAdapter.ViewHolder> {
    Context mContext;
    List<Guard> guards;
    Activity activity;
    public static final String GUARD_DATA="guard_data";

    public GuardAdapter(Context mContext, Activity activity, List<Guard> guards) {
        this.mContext = mContext;
        this.guards = guards;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.guard_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setTexts(guards.get(position),holder);
        setListeners(guards.get(position),holder);

    }

    private void setListeners(Guard guard, ViewHolder holder) {
        holder.displayGuardBtn.setOnClickListener(click->showGuardDetails(guard));
    }

    private void showGuardDetails(Guard guard) {
        Intent intent=new Intent(mContext, PastPatrolsActivity.class);
        intent.putExtra(GUARD_DATA,guard);
        activity.startActivity(intent);

    }

    private void setTexts(Guard guard, ViewHolder holder) {
        holder.guardName.setText(guard.getGuardName());
        //implement picasso library or Glide library to be able to load guard profile picture easily
        Picasso
                .with(mContext)
                .load(guard.getGuardProfilePicture())
               .into(holder.guardProfilePicture);
    }

    @Override
    public int getItemCount() {
        return guards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView guardProfilePicture;
        TextView guardName;
        ImageView displayGuardBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guardName=itemView.findViewById(R.id.txtGuardName);
            guardProfilePicture=itemView.findViewById(R.id.imgGuardProfilePic);
            displayGuardBtn=itemView.findViewById(R.id.imgDisplayGuard);

        }
    }
}
