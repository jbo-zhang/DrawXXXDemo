package com.example.practicedraw;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<FragmentHolder> holders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        initHolders();

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initHolders() {
        holders.add(new FragmentHolder("drawColor()", 0, new Practice1DrawColorView(this)));
        holders.add(new FragmentHolder("drawCircle()", 0, new Prectice2DrawCircle(this)));
        holders.add(new FragmentHolder("drawRect()", 0, new Prectice3DrawRectView(this)));
        holders.add(new FragmentHolder("drawPoint()", 0, new Prectice4DrawPointView(this)));
        holders.add(new FragmentHolder("drawOval()", 0, new Prectice5DrawOvalView(this)));
        holders.add(new FragmentHolder("drawLine()", 0, new Prectice6DrawLineView(this)));
        holders.add(new FragmentHolder("drawRoundRect()", 0, new Prectice7DrawRoundRectView(this)));
        holders.add(new FragmentHolder("drawArc()", 0, new Prectice8DrawArcView(this)));
        holders.add(new FragmentHolder("drawPath()", 0, new Prectice9DrawPathView(this)));
        holders.add(new FragmentHolder("直方图", 0, new Prectice10DrawHistogramView(this)));
        holders.add(new FragmentHolder("扇形图", 0, new Prectice11PieChartView(this)));

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private Context context;

        public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }


        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(holders.get(position).view);
        }

        @Override
        public int getCount() {
            return holders.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return holders.get(position).name;
        }
    }

    class FragmentHolder {
        String name;
        int layoutId;
        View view;
        FragmentHolder(String name, int layoutId, View view) {
            this.name = name;
            this.layoutId = layoutId;
            this.view = view;
        }
    }


}
