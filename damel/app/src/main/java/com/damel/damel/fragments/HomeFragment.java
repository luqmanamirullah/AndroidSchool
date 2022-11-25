package com.damel.damel.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.damel.damel.R;
import com.damel.damel.activities.ProfileActivity;
import com.damel.damel.activities.SearchActivity;
import com.damel.damel.adapters.CategoryAdapter;
import com.damel.damel.adapters.FeaturedAdapter;
import com.damel.damel.adapters.ForYouAdapter;
import com.damel.damel.models.Category;
import com.damel.damel.models.Featured;
import com.damel.damel.models.ForYou;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private final int MAX_CATEGORY = 8;
    private final Category[] categories = new Category[MAX_CATEGORY];

    private final int MAX_FOR_YOU = 4;
    private final ForYou[] forYous = new ForYou[MAX_FOR_YOU];

    private final int MAX_FEATURED = 3;
    private final Featured[] featureds = new Featured[MAX_FEATURED];

    private FirebaseUser user;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.hide();

        user = FirebaseAuth.getInstance().getCurrentUser();


        categories[0] = new Category("Kebersihan", R.drawable.ic_cleaning_service);
        categories[1] = new Category("Tukang", R.drawable.ic_plumbing);
        categories[2] = new Category("Pengasuh", R.drawable.ic_child);
        categories[3] = new Category("Les Privat", R.drawable.ic_pencil);
        categories[4] = new Category("Olahraga", R.drawable.ic_sports);
        categories[5] = new Category("Supir", R.drawable.ic_car);
        categories[6] = new Category("Peliharaan", R.drawable.ic_pets);
        categories[7] = new Category("Berkebun", R.drawable.ic_flower);

        forYous[0] = new ForYou("Tukang Pipa", "Rudiyansah", "https://s.kaskus.id/r480x480/images/fjb/2018/03/21/jasa_tukang_ledeng_pipa_kran_pompa_air_dll_berkualitas_malang_10097093_1521597448.jpg" , "Bandung", 4.5f);
        forYous[1] = new ForYou("Personal Trainer", "Jamal Aksan", "https://www.ireborn.co.id/wp-content/uploads/2019/07/Personal-Trainer-02-Finansialku.jpg", "Bandung", 4.8f);
        forYous[2] = new ForYou("Baby Sitter", "Melati", "https://res.cloudinary.com/dk0z4ums3/image/upload/v1608514977/attached_image/bunda-ini-panduan-memilih-dan-melatih-babysitter-untuk-si-kecil.jpg", "Bandung", 4.3f);
        forYous[3] = new ForYou("Tukang Kebun", "Dedi Sudrajat", "https://tradesmencosts.co.uk/wp-content/uploads/2022/01/gardener.jpg", "Bandung", 4.5f);

        featureds[0] = new Featured("Les gitar privat sampai bisa \uD83C\uDFB8", "Disini kita akan mempelajari cara bermain gitar dari teknik dasar hingga kalian bisa bermain shredding", "https://quantummusik.id/wp-content/uploads/2016/08/course4.jpg");
        featureds[1] = new Featured("Rumah kamu kotor? Kami siap membersihkannya \uD83E\uDDF9", "Jika kamu butuh bantuan untuk membersihkan sesuatu, kami siap membantu!", "https://myrobin.id/wp-content/uploads/2022/08/Cleaning-Service.jpg");
        featureds[2] = new Featured("Titipkan hewan peliharaan dengan aman \uD83D\uDC15", "Kami akan menjemput peliharaan anda dan merawatnya dengan baik di tempat kami selama kami sedang pergi", "https://drifttravel.com/wp-content/uploads/2021/05/egor-gordeev-dpTX9PxqGvY-unsplash-640x427.jpg");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final int columns = 4;
        RecyclerView rvCategory = view.findViewById(R.id.rv_category);
        rvCategory.setLayoutManager(new GridLayoutManager(getContext(), columns));
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories);
        rvCategory.setAdapter(categoryAdapter);

        RecyclerView rvForYou = view.findViewById(R.id.rv_foryou);
        rvForYou.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ForYouAdapter forYouAdapter = new ForYouAdapter(getContext(), forYous);
        rvForYou.setAdapter(forYouAdapter);

        RecyclerView rvFeatured = view.findViewById(R.id.rv_featured);
        rvFeatured.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(getContext(), featureds);
        rvFeatured.setAdapter(featuredAdapter);

        LinearLayout search = view.findViewById(R.id.search);
        CircleImageView profile = view.findViewById(R.id.profile);

        Uri photoUrl = user.getPhotoUrl();
        if (user != null && photoUrl != null) {
            profile.setPadding(10, 10, 10, 10);
            profile.setBorderWidth(3);
            profile.setBorderColor(getResources().getColor(R.color.white));
            Glide.with(getContext())
                    .load(photoUrl)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .dontAnimate()
                    .into(profile);
        }

        search.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        });

        return view;
    }

}
