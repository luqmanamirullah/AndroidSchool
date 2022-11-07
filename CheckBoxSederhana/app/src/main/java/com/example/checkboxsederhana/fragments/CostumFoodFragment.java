package com.example.checkboxsederhana.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.checkboxsederhana.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CostumFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CostumFoodFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CostumFoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CostumFoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CostumFoodFragment newInstance(String param1, String param2) {
        CostumFoodFragment fragment = new CostumFoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_costum_food, container, false);
        RadioButton radio_meaty = view.findViewById(R.id.radio1);
        RadioButton radio_bbq = view.findViewById(R.id.radio2);
        RadioButton radio_cheese = view.findViewById(R.id.radio3);
        RadioButton radio_supreme = view.findViewById(R.id.radio4);
        CheckBox checkBox_papperoni = view.findViewById(R.id.checbox1);
        CheckBox checkBox_tuna = view.findViewById(R.id.checbox2);
        CheckBox checkBox_veggie = view.findViewById(R.id.checbox3);
        CheckBox checkBox_american = view.findViewById(R.id.checbox4);
        CheckBox checkBox_meatlovers = view.findViewById(R.id.checbox5);
        CheckBox checkBox_supersupreme = view.findViewById(R.id.checbox6);
        CheckBox checkBox_cheeselovers = view.findViewById(R.id.checbox7);
        CheckBox checkBox_extramoz = view.findViewById(R.id.checbox8);
        FrameLayout frame_checker = view.findViewById(R.id.frame_checker);
        Button btn_orderfinish = view.findViewById(R.id.btn_orderfinish);
        TextView harga_pizza = view.findViewById(R.id.harga);
        TextView check_variant = view.findViewById(R.id.check_variant);
        TextView check_topping = view.findViewById(R.id.check_topping);
        TextView pembayaran = view.findViewById(R.id.pembayaran);
        TextView check_finish = view.findViewById(R.id.check_finish);
        btn_orderfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int harga = 0;
                String variant = null;
                String topping = " ";
                if (radio_meaty.isChecked()){
                    harga = 50000;
                    variant = "Meaty";
                } else if (radio_bbq.isChecked()){
                    harga = 55000;
                    variant = "BBQ Chicken";
                } else if ( radio_cheese.isChecked()){
                    harga = 60000;
                    variant = "Cheese";
                } else if (radio_supreme.isChecked()){
                    harga = 70000;
                    variant = "Supreme";
                } else {
                    harga = 0;
                }
                if (checkBox_papperoni.isChecked()){
                    harga += 7000;
                    topping += "+Paperoni" ;
                }
                if(checkBox_tuna.isChecked()){
                    harga += 10000;
                    topping += "+Tuna Melt ";
                }
                if(checkBox_veggie.isChecked()){
                    harga += 7000;
                    topping += "+Veggie Garden ";
                }
                if(checkBox_american.isChecked()){
                    harga += 10000;;
                    topping += "+American Favorite ";
                }
                if(checkBox_meatlovers.isChecked()){
                    harga += 12000;
                    topping += "+Meat Lovers ";
                }
                if(checkBox_supersupreme.isChecked()){
                    harga += 15000;
                    topping += "+Super Supreme ";
                }
                if(checkBox_cheeselovers.isChecked()){
                    harga += 15000;
                    topping += "+Chees Lovers ";
                }
                if(checkBox_extramoz.isChecked()){
                    harga += 20000;
                } else {
                    harga += 0;
                }
                harga_pizza.setText( "Rp." + harga);
                check_variant.setText(variant);
                check_topping.setText(topping);
                pembayaran.setText( "RP." + harga);
                frame_checker.setVisibility(frame_checker.VISIBLE);
                check_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frame_checker.setVisibility(frame_checker.GONE);
                    }
                });
            }
        });
        return view;
    }
}