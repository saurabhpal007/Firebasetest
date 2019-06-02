package com.pal.thirstymission.interntask;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
   RecyclerView recycler;
    ArrayList<User> list;
    User u;
    Adapter adapter;
    Button add;
    EditText ntxt,atxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler= findViewById(R.id.recycle);
        add=findViewById(R.id.add);
        ntxt=findViewById(R.id.nametext);
        atxt=findViewById(R.id.agetext);



        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        list = new ArrayList<>();


        db = FirebaseFirestore.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ntxt.getText().toString().trim();
                String age = atxt.getText().toString().trim();
                Date date = new Date();
                Timestamp ts=new Timestamp(date.getTime());

                User user = new User(
                        name,
                        Integer.parseInt(age),ts

                );
                Log.i("qqq","hiii");

                db.collection("USER").document("user1").set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "User Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(MainActivity.this, "User Not Added", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        db.collection("USER").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list2 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list2) {

                                User u = d.toObject(User.class);

                                list.add(u);
                                Log.i("lllllll",""+d);

                            }

                            adapter = new Adapter(MainActivity.this, list);
                            recycler.setAdapter(adapter);
                            adapter.notifyDataSetChanged();



                        }


                    }
                });








    }
}
