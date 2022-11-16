package com.example.smartpatroladmin.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatroladmin.DeleteGuardsActivity;
import com.example.smartpatroladmin.Firebase.FirebaseConstants;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.ViewHolder> {
    Context mContext;
    List<Guard> guards;
    Activity activity;

    public static final String GUARD_DATA="guard_data";

    public DeleteAdapter(DeleteGuardsActivity mContext, List<Guard> guards, Activity activity) {
        this.mContext = mContext;
        this.guards = guards;
        this.activity = activity;
    }

    public DeleteAdapter(DeleteGuardsActivity mContext, List<Guard> guards) {
        this.mContext = mContext;
        this.guards = guards;
        this.activity = activity;

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
        //delete information from recyclerView
        holder.imgDeleteGuard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseConstants.user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(mContext, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
               /* AlertDialog.Builder dialog=new AlertDialog.Builder(activity.getApplicationContext());
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in removing this account permanently from the system.");
*/
            }
        });
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
        ImageView imgDeleteGuard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guardName=itemView.findViewById(R.id.txtGuardName);
            guardProfilePicture=itemView.findViewById(R.id.imgGuardProfilePic);
            imgDeleteGuard=itemView.findViewById(R.id.imgDeleteGuard);

        }
    }
}
