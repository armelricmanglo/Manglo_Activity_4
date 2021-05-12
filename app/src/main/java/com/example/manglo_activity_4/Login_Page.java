package com.example.manglo_activity_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {
    EditText text_username_2, text_password_2;
    Button button_login, button_signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        text_username_2 = (EditText)findViewById(R.id.txt_username_2);
        text_password_2 = (EditText)findViewById(R.id.txt_password_2);

        button_login = (Button)findViewById(R.id.btn_login);
        button_signup = (Button)findViewById(R.id.btn_signup);

        DB = new DBHelper(this);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register_Page.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = text_username_2.getText().toString();
                String pass = text_password_2.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Login_Page.this, "Not all fields are answered", Toast.LENGTH_SHORT).show();

                else
                {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);

                    if (checkuserpass = true)
                    {
                        Toast.makeText(Login_Page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(Login_Page.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}