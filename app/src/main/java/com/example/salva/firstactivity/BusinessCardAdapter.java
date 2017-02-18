package com.example.salva.firstactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by salva on 15/02/2017.
 */
public class BusinessCardAdapter extends RecyclerView.Adapter<BusinessCardAdapter.BusinessCardVH> {


    private final Context context;
    public ArrayList<BusinessCard> dataSet = new ArrayList<>();
    Button button_call;
    Button button_go;
    Button button_send;


    public BusinessCardAdapter(Context c){
        context = c;
    }


    public void addBusinessCard(BusinessCard bc){

        dataSet.add(0,bc);
        notifyItemInserted(0);
    }

    public void setDataSet (ArrayList<BusinessCard> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public BusinessCardAdapter.BusinessCardVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_card,parent,false);
        return new BusinessCardVH(view);
    }


    @Override
    public void onBindViewHolder( BusinessCardAdapter.BusinessCardVH holder, int position) {

        BusinessCard businessCard = dataSet.get(position);
        holder.nameTv.setText(businessCard.getName());
        holder.emailTv.setText(businessCard.getEmail());
        holder.phoneNumberTv.setText(businessCard.getPhoneNumber());
        holder.courseTv.setText(businessCard.getCourse());
        holder.addressTv.setText(businessCard.getAddress());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }



    public class BusinessCardVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTv, phoneNumberTv,emailTv, courseTv, addressTv;
        Button infoBtn;


        public BusinessCardVH(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.username_TV);
            phoneNumberTv = (TextView) itemView.findViewById(R.id.phoneNumberTV);
            emailTv = (TextView)itemView.findViewById(R.id.ind_email);
            courseTv = (TextView)itemView.findViewById(R.id.course_TV);
            addressTv = (TextView)itemView.findViewById(R.id.AddressTV);
            infoBtn = (Button) itemView.findViewById(R.id.info_button);
            button_call= (Button)itemView.findViewById(R.id.button_call);
            button_go=(Button)itemView.findViewById(R.id.go_button);

//            String email = intent.getStringExtra("email");
//            ind_email=(TextView)itemView.findViewById(R.id.ind_email);
//            ind_email.setText(email);

            button_send=(Button)itemView.findViewById(R.id.send_button);
            infoBtn.setOnClickListener(this);
            button_call.setOnClickListener(this);
            button_send.setOnClickListener(this);
            button_go.setOnClickListener(this);
        }



            @Override
            public void onClick(View v) {

                if(v.getId()==R.id.button_call){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse("tel:"+phoneNumberTv.getText().toString());
                    intent.setData(uri);
                    itemView.getContext().startActivity(intent);
                }else if(v.getId()==R.id.go_button){

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse("geo:0,0?q="+addressTv.getText().toString());
                    intent.setData(uri);
                    itemView.getContext().startActivity(intent);
                }else if(v.getId() ==  R.id.send_button){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "AFFAMMOCC PACIò!");
                    itemView.getContext().startActivity(intent);

                }
                else if(v.getId() == R.id.info_button){

                    //ContactsActivity.showSnackBar(dataSet.get(getAdapterPosition()).getName());
                    Intent intent = new Intent(context,InfoActivity.class);
                    intent.putExtra("username",nameTv.getText().toString());
                    intent.putExtra("indice",getAdapterPosition());
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent,1);

                }



            }
    }
}