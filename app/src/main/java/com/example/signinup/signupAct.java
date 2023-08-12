package com.example.signinup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.signinup.databinding.ActivitySignupBinding;

public class signupAct extends AppCompatActivity {
    ActivitySignupBinding binding;
    dbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new dbHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirm = binding.signupConfirm.getText().toString();

                if (email.equals("") || password.equals("") || confirm.equals("")){
                    Toast.makeText(signupAct.this, "All field is mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirm)){
                        Boolean checkUserEmail = dbHelper.checkEmail(email);
                        if (checkUserEmail == false){
                            Boolean insert = dbHelper.insertData(email,password);
                            if (insert == true){
                                Toast.makeText(signupAct.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),signinAct.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signupAct.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signupAct.this, "User already exist, please signin", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signupAct.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.signinRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signinAct.class);
                startActivity(intent);
            }
        });
    }
}