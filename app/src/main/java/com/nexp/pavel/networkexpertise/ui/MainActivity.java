package com.nexp.pavel.networkexpertise.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.nexp.pavel.networkexpertise.R;
import com.nexp.pavel.networkexpertise.ui.adapters.myFragmentPageAdapter;
import com.nexp.pavel.networkexpertise.ui.fragments.MainFragment;
import com.nexp.pavel.networkexpertise.ui.fragments.StatFragment;
import com.nexp.pavel.networkexpertise.ui.fragments.TestFragment;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Ido ido;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabs);




        Fragment mainFragment = MainFragment.newInstance();
        Fragment testFragment = TestFragment.newInstance();
        Fragment statFragment = StatFragment.newInstance();


        myFragmentPageAdapter fragmentPageAdapter = new myFragmentPageAdapter(getSupportFragmentManager());
        fragmentPageAdapter.addFragment(mainFragment);
        fragmentPageAdapter.addFragment(testFragment);
        fragmentPageAdapter.addFragment(statFragment);

        mViewPager.setAdapter(fragmentPageAdapter);
        //tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        //mViewPager.setCurrentItem(1);


        if (getIntent().hasExtra("body")) {
            Intent intent = getIntent();
            String title = intent.getStringExtra("title");
            String body = intent.getStringExtra("body");

            Log.d("My Activity", body);
        }

//        if (getIntent().getExtras() != null) {
//            TestFragment fragment = (TestFragment) fragmentPageAdapter.getFragments().get(1);
//            TextView textView = fragment.getView().findViewById(R.id.tv_body);
//            textView.setText("sadsd");
//        }

    }



    public static final Intent newIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public String getMyData(String data) {
        return data;
    }

    public interface Ido {
         void doIt(String data);
    }

}
