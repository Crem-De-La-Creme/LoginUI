package com.example.tickats.loginui;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import android.widget.TextView;

public class Account extends AppCompatActivity {


    private static final String TAG="Account";
    private SectionPageAdapter mSectionPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Log.d(TAG,"onCreate: Starting.");


    }
    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter= new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab1(),"Account");
        adapter.addFragment(new tab2(),"Tickets");
        adapter.addFragment(new tab3(),"Contacts");
        viewPager.setAdapter(adapter);
    }
}
