package com.example.erpnext.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpnext.R;
import com.example.erpnext.ViewPagerAdapter;

public class OnboardingScreen extends AppCompatActivity {
    ViewPager mSlideViewPage;
    LinearLayout mDotLayout;
    AppCompatButton skipbtn, nextbtn;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);
        //getSupportActionBar().hide();

        skipbtn = findViewById(R.id.skip_btn);
        nextbtn = findViewById(R.id.next_btn);

        skipbtn.setOnClickListener(view -> {
            startActivity(new Intent(OnboardingScreen.this, Login.class));
            finish();
        });

        nextbtn.setOnClickListener(view -> {
            if (getItem(0) < 2){
                mSlideViewPage.setCurrentItem(getItem(1), true) ;
            }else {
                startActivity(new Intent(OnboardingScreen.this, Login.class));
                finish();
            }
        });

        mSlideViewPage = findViewById(R.id.viePager);
        mDotLayout = findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);

        mSlideViewPage.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        mSlideViewPage.addOnPageChangeListener(listener);
    }

    public void setUpIndicator(int position) {
        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            }
            mDotLayout.addView(dots[i]);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.white, getApplicationContext().getTheme()));
        }

    }
    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i){
        return mSlideViewPage.getCurrentItem() + i;
    }

}