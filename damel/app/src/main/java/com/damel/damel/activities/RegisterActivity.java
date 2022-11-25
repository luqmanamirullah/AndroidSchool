package com.damel.damel.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.damel.damel.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView tvLogin;
    private EditText etEmail, etPassword, etName, etPassConf;
    private MaterialButton btnGoogleSignIn;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setElevation(0);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            init();
        }
    }

    private void init() {
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.pass);
        etName = findViewById(R.id.nama);
        etPassConf = findViewById(R.id.konfir);
        btnGoogleSignIn = findViewById(R.id.gugel);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String passConf = etPassConf.getText().toString();

            boolean isValid = validate(name, email, password, passConf);
            if (!isValid) return;

            signUp(email, password, name);
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogleSignIn.setOnClickListener(v -> {
           googleSignIn();
        });

        tvLogin.setOnClickListener(v -> finish());
    }

    private void signUp(String email, String password, String name) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar");
        progressDialog.setCancelable(false);
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Map<String, Object> storeUser = new HashMap<>();
                                    storeUser.put("name", user.getDisplayName());
                                    storeUser.put("email", user.getEmail());
                                    storeUser.put("photoUrl", user.getPhotoUrl());
                                    storeUser.put("job", null);
                                    db.collection("users").document(user.getUid()).set(storeUser);

                                    signInSuccess();
                                    progressDialog.dismiss();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Daftar Gagal", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });

                } else {
                    Toast.makeText(RegisterActivity.this, "Daftar Gagal", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private boolean validate(String name, String email, String password, String passConf) {
        if (name.isEmpty()) {
            etName.setError("Wajib diisi");
            etName.requestFocus();
            return false;
        }

        if (name.length() > 30) {
            etName.setError("Nama terlalu panjang");
            etName.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            etEmail.setError("Wajib diisi");
            etEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Wajib diisi");
            etPassword.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            etPassword.setError("Minimal 8 karakter");
            etPassword.requestFocus();
            return false;
        }

        if (!passConf.equals(password)) {
            etPassConf.setError("Konfirmasi tidak sama");
            etPassConf.requestFocus();
            return false;
        }

        return true;
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("GOOGLE SIGN IN", "Firebase Auth with google: " + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w("GOOGLE SIGN IN", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null) ;
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        db.collection("users")
                                .whereEqualTo("email", user.getEmail())
                                .get()
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        QuerySnapshot querySnapshot = task1.getResult();
                                        if (querySnapshot.isEmpty()) {
                                            Map<String, Object> storeUser = new HashMap<>();
                                            storeUser.put("name", user.getDisplayName());
                                            storeUser.put("email", user.getEmail());
                                            storeUser.put("photoUrl", user.getPhotoUrl());
                                            storeUser.put("job", null);
                                            db.collection("users")
                                                    .document(user.getUid())
                                                    .set(storeUser)
                                                    .addOnCompleteListener(task2 -> {
                                                        if (task2.isSuccessful()) {
                                                            signInSuccess();
                                                            progressDialog.dismiss();
                                                        } else {
                                                            Toast.makeText(this, "Masuk Gagal", Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                        } else {
                                            signInSuccess();
                                            progressDialog.dismiss();
                                        }
                                    } else {
                                        Toast.makeText(this, "Masuk Gagal", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });

                    } else {
                        Toast.makeText(this, "Masuk Gagal", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    private void signInSuccess() {
        Toast.makeText(this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
