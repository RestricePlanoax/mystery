package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserList extends ArrayAdapter<UserDetails> {
    private Activity context;
    private List<UserDetails> userList;



    public UserList(Activity context, List<UserDetails> userList)
    {
        super(context, R.layout.activity_dashboard_specialist,userList);
        this.context = context;
        this.userList = userList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
         TextView textViewUserName = (TextView) listViewItem.findViewById(R.id.textViewName);
         TextView textViewGender = (TextView) listViewItem.findViewById(R.id.textViewGender);
         TextView textViewAgeGroup = (TextView) listViewItem.findViewById(R.id.textViewAgeGroup);
         TextView textViewCovid = (TextView) listViewItem.findViewById(R.id.textViewCovid);
         TextView textViewPreExist  = (TextView)listViewItem.findViewById(R.id.textViewPre);


         UserDetails user = userList.get(position);
         textViewUserName.setText("Name: " + user.getName());
         textViewGender.setText("Gender: " + user.getGender());
         textViewAgeGroup.setText("Age Group: " + user.getAgeGroup());
         textViewCovid.setText("Covid Vaccination done or not?: "+user.getCovid_vac());
         textViewPreExist.setText("Prexisting Conditions? " + user.getPre_existing());

         return listViewItem;
    }
}
