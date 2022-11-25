package com.damel.damel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.damel.damel.R;
import com.damel.damel.models.Intro;

public class IntroAdapter extends PagerAdapter {
    private Intro[] intros;
    private LayoutInflater layoutInflater;

    public IntroAdapter(Context context, Intro[] intros) {
        this.layoutInflater = LayoutInflater.from(context);
        this.intros = intros;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_intro, container, false);
        ((TextView) view.findViewById(R.id.title_intro)).setText(intros[position].title);
        ((TextView) view.findViewById(R.id.desc_intro)).setText(intros[position].desc);
        ((ImageView) view.findViewById(R.id.img_intro)).setImageResource(intros[position].image);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return intros.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
