package com.damel.damel.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.damel.damel.R;
import com.damel.damel.utils.Utils;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JobRegisterActivity extends AppCompatActivity {

    private String jobImageUrl;

    private TextInputEditText etJobName;
    private Spinner sJobCategory;
    private Spinner sJobCity;
    private TextInputEditText etJobPriceFrom;
    private TextInputEditText etJobPriceTo;
    private TextInputEditText etJobAddress;
    private TextInputEditText etJobSlogan;
    private TextInputEditText etJobDesc;
    private TextInputEditText etTimeFrom;
    private TextInputEditText etTimeTo;
    private TextView tvAddImage;
    private ImageView ivJobImage;
    private MaterialButton btnJobReg;

    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imageRef;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private DocumentReference jobRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_job_register);

        user = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        jobImageUrl = user.getUid().toString() + ".jpg";
        imageRef = storageRef.child(jobImageUrl);
        db = FirebaseFirestore.getInstance();
        jobRef = db.collection("users").document(user.getUid());

        etJobName = findViewById(R.id.job_name);
        sJobCategory = findViewById(R.id.job_category);
        sJobCity = findViewById(R.id.job_city);
        etJobPriceFrom = findViewById(R.id.job_price_from);
        etJobPriceTo = findViewById(R.id.job_price_to);
        etJobAddress = findViewById(R.id.job_address);
        etJobSlogan = findViewById(R.id.job_slogan);
        etJobDesc = findViewById(R.id.job_desc);
        etTimeFrom = findViewById(R.id.job_time_from);
        etTimeTo = findViewById(R.id.job_time_to);
        tvAddImage = findViewById(R.id.job_add_image);
        ivJobImage = findViewById(R.id.job_image);
        btnJobReg = findViewById(R.id.job_btn);

        tvAddImage.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop(4f, 3f)
                    .compress(1080)
                    .maxResultSize(1080, 1080)
                    .start();
        });

        btnJobReg.setOnClickListener(v -> {

            if (validate()) {

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Tunggu sebentar");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Map<String, Object> job = new HashMap<>();

                if (ivJobImage.getDrawable() != null) {
                    job.put("imageUrl", jobImageUrl);
                } else {
                    job.put("imageUrl", null);
                }
                job.put("name", etJobName.getText().toString());
                job.put("category", sJobCategory.getSelectedItem().toString());
                job.put("city", sJobCity.getSelectedItem().toString());
                job.put("price_from", etJobPriceFrom.getText().toString());
                job.put("price_to", etJobPriceFrom.getText().toString());
                job.put("time_from", etTimeFrom.getText().toString());
                job.put("time_to", etTimeTo.getText().toString());
                job.put("address", etJobAddress.getText().toString());
                job.put("slogan", etJobSlogan.getText().toString());
                job.put("desc", etJobDesc.getText().toString());

                jobRef.update("job", job).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (ivJobImage.getDrawable() != null) {
                            ivJobImage.setDrawingCacheEnabled(true);
                            ivJobImage.buildDrawingCache();
                            Bitmap bitmap = ((BitmapDrawable) ivJobImage.getDrawable()).getBitmap();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] data = baos.toByteArray();

                            UploadTask uploadTask = imageRef.putBytes(data);
                            uploadTask.addOnFailureListener(exception -> {
                                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }).addOnSuccessListener(taskSnapshot -> {
                                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                finish();
                            });
                        } else {
                            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    } else {
                        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });

        etTimeFrom.setClickable(true);
        etTimeFrom.setLongClickable(false);
        etTimeFrom.setInputType(InputType.TYPE_NULL);

        etTimeTo.setClickable(true);
        etTimeTo.setLongClickable(false);
        etTimeTo.setInputType(InputType.TYPE_NULL);

        etTimeFrom.setOnClickListener(v -> showTimeDialog(etTimeFrom));
        etTimeFrom.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showTimeDialog(etTimeFrom);
        });

        etTimeTo.setOnClickListener(v -> showTimeDialog(etTimeTo));
        etTimeTo.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showTimeDialog(etTimeTo);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            ivJobImage.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Gagal menambahkan gambar", Toast.LENGTH_SHORT).show();
        }
    }

    private void showTimeDialog(TextInputEditText timeInput) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> timeInput.setText(hourOfDay + ":" + minute),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    private boolean validate() {
        if (!Utils.required(etJobName)) return false;
        if (!Utils.required(etJobPriceFrom)) return false;
        if (!Utils.required(etJobPriceTo)) return false;
        if (!Utils.required(etTimeFrom)) return false;
        if (!Utils.required(etTimeTo)) return false;
        if (!Utils.required(etJobAddress)) return false;
        if (!Utils.required(etJobSlogan)) return false;
        if (!Utils.required(etJobDesc)) return false;
        if (!Utils.required(etJobName)) return false;
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}