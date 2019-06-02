package com.pal.thirstymission.interntask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Programholder>{
    Context context;
   ArrayList<User> users;
   public Adapter(Context c,ArrayList<User> u)
   {

      this.context =c;
       this.users=u;

   }
    @NonNull
    @Override
    public Programholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.list,viewGroup,false);
        return new Programholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programholder programholder, int i) {
       User user=users.get(i);
        programholder.txts.setText("Name:"+user.getName());
        programholder.txt2.setText("Age:"+user.getAge());
        Log.i("yyyy",""+user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Programholder extends RecyclerView.ViewHolder{

        TextView txts,txt2;
        public Programholder(@NonNull View itemView) {
            super(itemView);

            txts= itemView.findViewById(R.id.name);
            txt2= itemView.findViewById(R.id.age);

        }
    }

}
