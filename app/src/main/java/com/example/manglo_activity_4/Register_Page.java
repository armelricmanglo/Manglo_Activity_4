package com.example.manglo_activity_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Page extends AppCompatActivity {

    EditText text_username, text_password, text_retype_password;
    Button button_register, button_return;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__page);

        text_username = (EditText)findViewById(R.id.txt_username);
        text_password = (EditText)findViewById(R.id.txt_password);
        text_retype_password = (EditText)findViewById(R.id.txt_retype_password);

        button_register = (Button)findViewById(R.id.btn_register);
        button_return = (Button)findViewById(R.id.btn_return);

        DB = new DBHelper(this);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = text_username.getText().toString();
                String pass = text_password.getText().toString();
                String repass = text_retype_password.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Register_Page.this, "Not all fields are answered", Toast.LENGTH_SHORT).show();

                else{

                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);

                        if (checkuser==false){
                            Boolean insert = DB.insertData(user, pass);

                            if (insert==true){
                                Toast.makeText(Register_Page.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(Register_Page.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                        else{
                            Toast.makeText(Register_Page.this, "Username is already used", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        Toast.makeText(Register_Page.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_Page.class);
                startActivity(intent);
            }
        });

    }
}