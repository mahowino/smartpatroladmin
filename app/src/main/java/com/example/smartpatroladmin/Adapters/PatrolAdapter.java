package com.example.smartpatroladmin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatroladmin.Interface.PatrolRetriever;
import com.example.smartpatroladmin.Models.Patrols;
import com.example.smartpatroladmin.R;
import com.squareup.picasso.Picasso;

import org.checkerframework.dataflow.qual.TerminatesExecution;

import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class PatrolAdapter extends RecyclerView.Adapter<PatrolAdapter.HolderView> {
    Context mContext;
    List<Patrols> patrols;

    public PatrolAdapter(Context mContext, List<Patrols> patrols) {
        this.mContext = mContext;
        this.patrols = patrols;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderView(LayoutInflater.from(mContext).inflate(R.layout.patrol_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        setTexts(patrols.get(position),holder);

    }

    private void setTexts(Patrols patrols, HolderView holder) {
        //holder.time.setText((CharSequence) patrols.getStartingTime());
        holder.locationStart.setText(patrols.getStartingLocation());
        holder.locationEnd.setText(patrols.getEndingLocation());

        Picasso
                .with(mContext)
                //.load(guard.getGuardProfilePicture())
                .load(R.drawable.icons8_police_car_64px)
                .into(holder.imgIncidentImageView);
    }

    @Override
    public int getItemCount() {
        return patrols.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
      TextView locationStart, locationEnd,time;
      ImageView imgIncidentImageView;
        public HolderView(@NonNull View itemView) {
            super(itemView);
            locationStart=itemView.findViewById(R.id.txtLocationPatrolStart);
            locationEnd=itemView.findViewById(R.id.txtLocationPatrolEnd);
           // time=itemView.findViewById(R.id.txtPatrolDate);
            imgIncidentImageView=itemView.findViewById(R.id.imgIncidentImageView);

        }
    }
}
