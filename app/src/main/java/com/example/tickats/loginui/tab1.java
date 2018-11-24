package com.example.tickats.loginui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class tab1 extends Fragment {
    private static final String TAG="tab1";
    private TextView emplID,empName,empLastName,hoursworked;
    private ImageView profilepic;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstantState){
        View view= inflater.inflate(R.layout.fragment_tab1,container,false);
        emplID=view.findViewById(R.id.etEmpID);
        empName=view.findViewById(R.id.etEmpName);
        empLastName=view.findViewById(R.id.etEmpLastName);
        hoursworked=view.findViewById(R.id.etsumHours);
        profilepic=view.findViewById(R.id.etProfilePic);





        return view;
    }
    public String setText(String entry){

    }

}
