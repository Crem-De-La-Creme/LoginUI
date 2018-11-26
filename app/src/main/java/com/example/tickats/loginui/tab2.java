package com.example.tickats.loginui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;


public class tab2 extends Fragment {
    private static final String TAG="Tab1Fragment";

    // Arrays that hold the ticket data
    public static ArrayList<String> mTicketID = new ArrayList<>();
    public static ArrayList<String> mWorksite = new ArrayList<>();
    public static ArrayList<String> mPriority = new ArrayList<>();
    private String FWid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstantState){
        View view= inflater.inflate(R.layout.fragment_tab2,container,false);

            // Initialize Vew Ticket Button Will pass ticket ID to View full ticket module
            Button ViewFullTicket = (Button)view.findViewById(R.id.ViewTicketButton);


            //This is where you will get the ticket ID
            view.findViewById(R.id.TicketIdEntry);

            AddTicketInfo();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initRecyclerView(view);
            return view;
        }

        private void initRecyclerView (View view){
            RecyclerView TicketViewer = view.findViewById(R.id.TicketViewer);
            RelativeRecyclerAdapter adapter = new RelativeRecyclerAdapter(mTicketID, mWorksite, mPriority);
            TicketViewer.setAdapter(adapter);
            TicketViewer.setLayoutManager(new LinearLayoutManager(getActivity()));

        }

        private void AddTicketInfo (){
            // THIS IS WHERE YOU NEED TO PUT THE GETTER
            String FWid = "4"; // FWid will need to be changed to accept input from login
            BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
            backgroundWorker.execute("TicketStart", FWid);

        }


}



