package com.example.smartpatroladmin.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.smartpatroladmin.Firebase.FirebaseRepository;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.R;
import com.example.smartpatroladmin.ViewGuardsActivity;
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


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.delete_card, parent, false));
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
                FirebaseConstants.db.collection("Guard").document(guard.getuId()).delete()
                        .addOnSuccessListener(unused ->{
                            Toast.makeText(mContext, "user deleted successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(mContext, ViewGuardsActivity.class);
                            activity.startActivity(intent);


                        } )
                        .addOnFailureListener(e ->{} );
                }});
               /* AlertDialog.Builder dialog=new AlertDialog.Builder(activity.getApplicationContext());
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in removing this account permanently from the system.");
*/

    }

    private void setTexts(Guard guard, ViewHolder holder) {
        holder.guardName.setText(guard.getGuardName());
        //implement picasso library or Glide library to be able to load guard profile picture easily
     /*   Picasso
                .with(mContext)
                .load(guard.getGuardProfilePicture())
                .into(holder.guardProfilePicture);
        Picasso
                .with(mContext)
                .load(guard.getGuardProfilePicture())
                .into(holder.imgDeleteGuard);*/
    } //implement picasso library or Glide library to be able to load guard profile picture easily


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
