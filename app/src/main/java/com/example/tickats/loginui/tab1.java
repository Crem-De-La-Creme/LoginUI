package com.example.tickats.loginui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class tab1 extends Fragment {
    private static final String TAG="tab1";
    private Context context;
    private TextView emplID,empName,empLastName,hoursworked;
    private ImageView profilepic;
    private Button Logout;
    private EditText username,password;
    public static String fid,fname,lname,hrs;







    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstantState){

        View view= inflater.inflate(R.layout.fragment_tab1,container,false);




        emplID= (TextView) view.findViewById(R.id.etEmpID);
        empName= (TextView) view.findViewById(R.id.etEmpName);
        empLastName= (TextView) view.findViewById(R.id.etEmpLastName);
        hoursworked= (TextView) view.findViewById(R.id.etsumHours);
        profilepic= view.findViewById(R.id.etProfilePic);
        Logout=view.findViewById(R.id.etLogout);

        emplID.setText(fid);
        empName.setText(fname);
        empLastName.setText(lname);
        hoursworked.setText(hrs);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);


            }

        });

        return view;
    }


}
