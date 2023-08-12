package com.example.signinup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.signinup.databinding.ActivitySigninBinding;

public class signinAct extends AppCompatActivity {
    ActivitySigninBinding binding;
    dbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new dbHelper(this);

        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signinEmail.getText().toString();
                String password = binding.signinPassword.getText().toString();
                if (email.equals("") || password.equals("")){
                    Toast.makeText(signinAct.this, "All field are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkCredential = dbHelper.checkEmailPassword(email,password);
                    if (checkCredential == true){
                        Toast.makeText(signinAct.this, "Signin successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(signinAct.this, "Invalid credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signupAct.class);
                startActivity(intent);
            }
        });
    }
}