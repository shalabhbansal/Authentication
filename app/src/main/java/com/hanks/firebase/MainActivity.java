package com.hanks.firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register ;
    private FirebaseAuth firebaseAuth ;
    private EditText Email ;
    private EditText password ;
    private TextView signin ;
    private ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        register = (Button) findViewById(R.id.register);
        Email = (EditText) findViewById(R.id.email_verify);
        password = (EditText) findViewById(R.id.password);
        signin = (TextView) findViewById(R.id.signup);
       register.setOnClickListener(this);
       signin.setOnClickListener(this);
    }

    private void registerUser()
    {
        String email = Email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show();
            //empty email
            return;
        }

        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            //empty password
            return;
        }
       progressDialog.setMessage("registering User....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()) {
                   Toast.makeText(MainActivity.this,"Successful Registeration",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this," Registeration unsuccessful",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == register)
        {
            registerUser();
        }
        if(v == signin)
        {
            //open new activity
        }
    }
}
