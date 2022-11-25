package com.damel.damel.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damel.damel.R;
import com.damel.damel.activities.JobRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.C;

public class JobFragment extends Fragment {

    private FirebaseUser user;
    private FirebaseFirestore db;
    private DocumentReference userRef;

    private View view;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        db = FirebaseFirestore.getInstance();
//        userRef = db.collection("users").document(user.getUid());
//        userRef.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                DocumentSnapshot document = task.getResult();
//                if (document.exists()) {
//                    if (document.getData().get("job") != null) {
////                        view = inflater.inflate(R.layout.fragment_no_job, container, false);
////                        MaterialButton btnJobRegister = view.findViewById(R.id.btn_to_job_register);
////                        btnJobRegister.setOnClickListener(v -> startActivity(new Intent(getContext(), JobRegisterActivity.class)));
//                        replaceFragment(new ChatFragment());
//                    } else {
////                        view = inflater.inflate(R.layout.fragment_job, container, false);
//                    }
//                } else {
////                    view = inflater.inflate(R.layout.error, container, false);
//                }
//            } else {
////                view = inflater.inflate(R.layout.error, container, false);
//            }
//        });
//    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(false);
        actionBar.setTitle("Pekerjaan");
        actionBar.show();


        view = inflater.inflate(R.layout.fragment_no_job, container, false);
        MaterialButton btnJobRegister = view.findViewById(R.id.btn_to_job_register);
        btnJobRegister.setOnClickListener(v -> startActivity(new Intent(getContext(), JobRegisterActivity.class)));
//        replaceFragment(new LoadingFragment());
        return view;

    }

//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit();
//    }

}
