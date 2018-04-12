package com.example.mir.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView name, email, college;
    FirebaseFirestore db;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        college = (TextView)findViewById(R.id.college);




        signin = (Button) findViewById(R.id.button2);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name, Email, College;
                Name = name.getText().toString();
                Email = email.getText().toString();
                College = college.getText().toString();

                Map<String,Object> user = new HashMap<>();
                user.put("Name",Name);
                user.put("College",College);
                user.put("Email",Email);

                db.collection("Users")
                        .document(Name)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Sign in", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Couldn't Sign in", Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(v.getContext(), ViewDetail.class);
                startActivity(intent);

            }
        });
    }
}
