package com.example.salva.firstactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by salva on 10/02/2017.
 */

public class ContactsActivity extends Activity {

    Button button_call;
    TextView phoneNumberTv;
    TextView addressTV;

    View.OnClickListener callClickListener = new View.OnClickListener (){
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.button_call){

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("tel:"+phoneNumberTv.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }else if(v.getId()==R.id.go_button){

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("geo:0,07q="+ addressTV.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        phoneNumberTv=(TextView)findViewById(R.id.phoneNumberTV);
        button_call=(Button)findViewById(R.id.button_call);
        addressTV=(TextView)findViewById(R.id.AddressTV);

        button_call.setOnClickListener(callClickListener);
    }
}
