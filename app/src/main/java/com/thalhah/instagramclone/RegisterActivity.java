package com.thalhah.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;
    @BindView(R.id.pp_profile)
    CircleImageView ppProfile;
    @BindView(R.id.txtemailreg)
    TextInputEditText txtemailreg;
    @BindView(R.id.txtusernamereg)
    TextInputEditText txtusernamereg;
    @BindView(R.id.txtpassreg)
    TextInputEditText txtpassreg;
    @BindView(R.id.btnregister)
    MaterialButton btnregister;
    @BindView(R.id.txtloginreg)
    TextView txtloginreg;
    @BindView(R.id.parentrootregsiter)
    RelativeLayout parentrootregsiter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        parentrootregsiter = findViewById(R.id.parentrootregsiter);

        // onCreate
        animationDrawable = (AnimationDrawable) parentrootregsiter.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

        btnregister.setOnClickListener(view -> insertData());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onResume
        animationDrawable.start();
    }

    @OnClick(R.id.txtloginreg)
    public void onViewClicked() {
        goToLogin();
    }

    private void goToLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    private void insertData() {
        if (TextUtils.isEmpty(Objects.requireNonNull(txtemailreg.getText()).toString())) {
            txtemailreg.setError("Email harus diisi");
            txtemailreg.requestFocus();
        } else if (TextUtils.isEmpty(Objects.requireNonNull(txtusernamereg.getText()).toString())) {
            txtusernamereg.setError("Username harus diisi");
            txtusernamereg.requestFocus();
        } else if (TextUtils.isEmpty(Objects.requireNonNull(txtpassreg.getText()).toString())) {
            txtpassreg.setError("Password harus diisi");
            txtpassreg.requestFocus();
        } else {
            String URL = "https://igclone.000webhostapp.com/api_registeruser.php";

            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setTitle("Memproses data");
            progressDialog.setMessage("Tunggu sebentar ...");
            progressDialog.show();

            StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, URL,
                    response -> {
                        progressDialog.dismiss();
                        Log.d("log", "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String hasil = jsonObject.getString("hasil");
                            String pesan = jsonObject.getString("pesan");
                            if (hasil.equalsIgnoreCase("true")) {
                                goToLogin();
                            } else {
                                Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("log", "onResponse: " + e.getMessage());
                            Toast.makeText(this, "Terjadi kesalahan, coba lagi", Toast.LENGTH_SHORT).show();
                        }
                    }, error -> {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Terjadi kesalahan, coba lagi", Toast.LENGTH_LONG).show();
                Log.e("log", "onErrorResponse: " + error.getMessage());
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parameter = new HashMap<>();
                    parameter.put("username", txtusernamereg.getText().toString());
                    parameter.put("email", txtemailreg.getText().toString());
                    parameter.put("password", txtpassreg.getText().toString());

                    return parameter;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(RegisterActivity.this));
            requestQueue.add(jsonObjectRequest);
        }
    }
}
