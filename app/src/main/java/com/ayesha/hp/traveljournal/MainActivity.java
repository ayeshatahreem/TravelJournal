package com.ayesha.hp.traveljournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity
{
    Button btnSignIn,btnSignUp;
    LoginDatabaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDataBaseAdapter=new LoginDatabaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),SignUp.class);
                startActivity(in);
            }
        });
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.signin);
        dialog.setTitle("Sign in");
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String storedPassword=loginDataBaseAdapter.getSingleEntry(userName);
                if(password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent ui=new Intent(getApplicationContext(),MainUserInterface.class);
                    startActivity(ui);
                }
                else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
