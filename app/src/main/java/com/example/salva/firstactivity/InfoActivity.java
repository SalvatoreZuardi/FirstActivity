package com.example.salva.firstactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.onClick;

/**
 * Created by salva on 17/02/2017.
 */

public class InfoActivity extends AppCompatActivity  {
    Intent intent;
    TextView username_TV;
    EditText textTitle;
    int indice;
    String nome;
    Button ok;
    String risultato;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        indice = getIntent().getExtras().getInt("indice");
        nome=getIntent().getStringExtra(ContactsActivity.USERNAME);
        intent = getIntent();
        String username = intent.getStringExtra("username");
        username_TV=(TextView)findViewById(R.id.username_info_TV);
        username_TV.setText(username);
        textTitle=(EditText)findViewById(R.id.insert_et);
        setTitle(textTitle.getText().toString());
        username_TV.setText(nome);

        textTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String title = textTitle.getText().toString();
                int lungh=10;
                if(title.length()<10) {
                    setTitle(title);
                }
                else{
                    Toast.makeText(InfoActivity.this, R.string.toast3, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button ok=(Button)findViewById(R.id.button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.button){
                    Intent intent = new Intent();
                    risultato = textTitle.getText().toString();
                    intent.putExtra("risultato",risultato);
                    intent.putExtra("indice",indice);
                    setResult(1,intent);
                    finish();

                }
            }
        });


}



}
