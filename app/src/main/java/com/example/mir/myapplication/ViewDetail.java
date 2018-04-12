package com.example.mir.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;


public class ViewDetail extends AppCompatActivity {

    TextView name, email, college;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        college = (TextView)findViewById(R.id.college);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("Users")
                .document("abbas");

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("FirestoreDemo", "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    Log.d("FirestoreDemo", "Current data: " + snapshot.getData());
                    String userName = snapshot.getData().get("Name").toString();
                    name.setText(userName);

                    String userEmail = snapshot.getData().get("Email").toString();
                    email.setText(userEmail);

                    String userCollege = snapshot.getData().get("College").toString();
                    college.setText(userCollege);
                } else {
                    Log.d("FirestoreDemo", "Current data: null");
                }
            }
        });


    }
}
