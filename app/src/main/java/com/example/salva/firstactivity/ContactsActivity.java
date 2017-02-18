package com.example.salva.firstactivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by salva on 10/02/2017.
 */

public class ContactsActivity extends Activity {


    Intent intent;

    static RelativeLayout layout;

    RecyclerView businessCardsRV;
    LinearLayoutManager layoutManager;
    BusinessCardAdapter adapter;


    private static final String ELIS_ADDRESS = "via Sandro Sandri 71";
    private static final String LTM_COURSE = "LTM 11";
    public static final String USERNAME = "username";



    public void showAddStudentDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_student_add, null);
        dialogBuilder.setView(dialogView);

        final EditText studentName = (EditText) dialogView.findViewById(R.id.dialog_student_name);
        final EditText studentEmail = (EditText) dialogView.findViewById(R.id.dialog_student_email);
        final EditText studentPhone = (EditText) dialogView.findViewById(R.id.dialog_student_phone);

        dialogBuilder.setTitle(R.string.student);
        dialogBuilder.setMessage(R.string.insert_student_name);
        dialogBuilder.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();

                BusinessCard businessCard = new BusinessCard(studentName.getText().toString(),
                        studentEmail.getText().toString(),studentPhone.getText().toString(),LTM_COURSE,ELIS_ADDRESS);
                adapter.addBusinessCard(businessCard);
                businessCardsRV.scrollToPosition(0);

            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        dialogBuilder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        intent = getIntent();
        layout = (RelativeLayout) findViewById(R.id.contacts_layout);

        businessCardsRV = (RecyclerView) findViewById(R.id.business_cards_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new BusinessCardAdapter(this);
        businessCardsRV.setLayoutManager(layoutManager);
        businessCardsRV.setAdapter(adapter);
        adapter.setDataSet(getBusinessCards());
        findViewById(R.id.add_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddStudentDialog();

            }
        });




    }

//    public static void  showSnackBar(String name){
//
//        Snackbar snackbar = Snackbar.make(layout,name,Snackbar.LENGTH_SHORT);
//        snackbar.setAction("OK", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        snackbar.show();
//
//    }
    @Override
    protected void onStart() {
        super.onStart();


    }


    private ArrayList<BusinessCard> getBusinessCards() {
        ArrayList<BusinessCard> businessCards = new ArrayList<>();
        BusinessCard francescoBC = new BusinessCard("Francesco Cipolla", "francescocpll@gmail.com", "333281213", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard matteoBC = new BusinessCard("Matteo Manfreda", "manfredamatteo44@gmail.com", "348974379", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard micheleBC = new BusinessCard("Michele Foderaro", "michele.foderaro@virgilio.it", "3891379123", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard domenicoBC = new BusinessCard("Domenico Licciardi", "licciardi.domenico98@gmail.com", "333281213", LTM_COURSE, ELIS_ADDRESS);
        BusinessCard gaetanoBC = new BusinessCard("Gaetano Ciccone", "gaetano.ciccone97@gmail.com", "333281213", LTM_COURSE, ELIS_ADDRESS);

        businessCards.add(francescoBC);
        businessCards.add(matteoBC);
        businessCards.add(micheleBC);
        businessCards.add(domenicoBC);
        businessCards.add(gaetanoBC);

        return businessCards;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BusinessCard bc = adapter.dataSet.get(data.getExtras().getInt("indice"));
        bc.setName(data.getExtras().getString("risultato"));
        adapter.notifyItemChanged(data.getExtras().getInt("indice"));
    }
}
