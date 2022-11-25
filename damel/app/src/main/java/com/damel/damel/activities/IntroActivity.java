package com.damel.damel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.damel.damel.R;
import com.damel.damel.adapters.IntroAdapter;
import com.damel.damel.models.Intro;

public class IntroActivity extends AppCompatActivity {

    private static final int MAX_STEP = 3;
    private Intro[] intros = new Intro[MAX_STEP];

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Damel);
        getSupportActionBar().hide();

        intros[0] = new Intro("Kerja", "Cari kerja dengan mudah dan bantu komunitas di sekitarmu", R.drawable.intro_work);
        intros[1] = new Intro("Sewa", "Sewa jasa yang kamu butuhkan untuk meringankan pekerjaanmu", R.drawable.intro_rent);
        intros[2] = new Intro("Komunitas", "Dari komunitas untuk komunitas. Kami di sini untuk berkontribusi", R.drawable.intro_community);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (!isFirstRun) {
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_intro);
            initComponent();
        }
    }

    private void initComponent() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        btnStart = findViewById(R.id.btn_start);
        bottomProgressDots(0);

        IntroAdapter introViewPagerAdapter = new IntroAdapter(IntroActivity.this, intros);
        viewPager.setAdapter(introViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnStart.setVisibility(View.GONE);
        btnStart.setOnClickListener(v -> {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_skip).setOnClickListener(v -> {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void bottomProgressDots(int index) {
        LinearLayout dotsLayout = findViewById(R.id.layout_dots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int widthHeight = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(widthHeight, widthHeight));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.intro_dot);
            dots[i].setColorFilter(getResources().getColor(R.color.light_gray), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        dots[index].setImageResource(R.drawable.intro_dot);
        dots[index].setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_IN);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
            if (position == MAX_STEP - 1) {
                btnStart.setVisibility(View.VISIBLE);
            } else {
                btnStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
