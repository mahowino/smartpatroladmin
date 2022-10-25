package com.example.smartpatroladmin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatroladmin.Models.Incidents;
import com.example.smartpatroladmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder> {
    Context mContext;
    List<Incidents> incidents;

    public IncidentAdapter(Context mContext, List<Incidents> incidents) {
        this.mContext = mContext;
        this.incidents = incidents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.incident_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Incidents incident=incidents.get(position);
        holder.incidentTitle.setText(incident.getTitle());
        holder.incidentMessage.setText(incident.getMessage());

        Picasso.with(mContext)
                .load(incident.getGuard().getGuardProfilePicture())
                .into(holder.incidentImageView);
    }

    @Override
    public int getItemCount() {
        return incidents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView incidentTitle,incidentMessage;
        ImageView incidentImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            incidentTitle=itemView.findViewById(R.id.txtIncidentTitle);
            incidentMessage=itemView.findViewById(R.id.txtIncidentMessage);
            incidentImageView=itemView.findViewById(R.id.imgIncidentImageView);
        }
    }
}
