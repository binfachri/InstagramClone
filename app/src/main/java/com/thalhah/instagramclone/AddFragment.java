package com.thalhah.instagramclone;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {


    public AddFragment() {
        // Required empty public constructor
    }

    private TextInputEditText inGambar, inCaption, inUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inCaption = view.findViewById(R.id.incaption);
        inGambar = view.findViewById(R.id.ingambar);
        inUser = view.findViewById(R.id.inuser);
        Button btnSimpan = view.findViewById(R.id.btnsimpan);

        btnSimpan.setOnClickListener(view1 -> insertData());
    }

    private void insertData() {
        if (TextUtils.isEmpty(Objects.requireNonNull(inGambar.getText()).toString())) {
            inGambar.setError("Gambar harus diisi");
            inGambar.requestFocus();
        } else if (TextUtils.isEmpty(Objects.requireNonNull(inUser.getText()).toString())) {
            inUser.setError("User harus diisi");
            inUser.requestFocus();
        } else {
            String URL = "https://igclone.000webhostapp.com/apisimpanpost.php";

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Menyimpan data");
            progressDialog.setMessage("Tunggu sebentar ...");
            progressDialog.show();

            StringRequest jsonObjectRequest =  new StringRequest(Request.Method.POST, URL,
                    response -> {
                        progressDialog.dismiss();
                        Log.d("log", "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String hasil = jsonObject.getString("hasil");
                            String pesan = jsonObject.getString("pesan");
                            if (hasil.equalsIgnoreCase("true")) {
                                Toast.makeText(getActivity(), pesan, Toast.LENGTH_LONG).show();

                                ((MainActivity) Objects.requireNonNull(getActivity())).openFragment(new HomeFragment());
                            } else {
                                Toast.makeText(getActivity(), pesan, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("log", "onResponse: " + e.getMessage());
                        }
                    }, error -> {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Terjadi kesalahan, coba lagi", Toast.LENGTH_LONG).show();
                        Log.e("log", "onErrorResponse: " + error.getMessage() );
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parameter = new HashMap<>();
                    parameter.put("gambar", inGambar.getText().toString());
                    parameter.put("caption", Objects.requireNonNull(inCaption.getText()).toString());
                    parameter.put("iduser", inUser.getText().toString());

                    return parameter;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
            requestQueue.add(jsonObjectRequest);
        }
    }
}
