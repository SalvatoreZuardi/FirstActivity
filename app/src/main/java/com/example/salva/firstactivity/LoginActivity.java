package com.example.salva.firstactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by salva on 10/02/2017.
 */

public class LoginActivity extends Activity{
    EditText username_ET;
    EditText password_ET;


    Button login_btn;

    View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = username_ET.getText().toString();
            String password = password_ET.getText().toString();
            if (!(username_ET.getText().toString().isEmpty() || password_ET.getText().toString().isEmpty())) {
                if (doLogin(username, password)) {
                    Toast.makeText(LoginActivity.this, R.string.toast, Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(LoginActivity.this, ContactsActivity.class);
                intent.putExtra("email",username);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this, R.string.toast2, Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_login);

        username_ET=(EditText)findViewById(R.id.login_username_et);
        password_ET=(EditText)findViewById(R.id.login_password_et);
        login_btn=(Button)findViewById(R.id.login_btn);

            login_btn.setOnClickListener(loginClickListener);

    }

    private boolean doLogin(String username, String password) {
        return true;
    }

}
