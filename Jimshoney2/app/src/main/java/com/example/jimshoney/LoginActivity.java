package com.example.jimshoney;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPass;
    Button btnLog, btnregis;
    ProgressBar progressBar;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;
    List<Login> loginActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPass);
        progressBar = findViewById(R.id.progressBar);

        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBarang();
            }
        });

    }

    private void LoginBarang() {
        String username = etUsername.getText().toString();
        String pass = etPass.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Masukan Username");
            etUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            etPass.setError("Masukan Password");
            etPass.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("pass", pass);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_LOGIN_BARANG, params, CODE_POST_REQUEST);
        request.execute();

    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        this.url =url;
        this.params =params;
        this.requestCode =requestCode;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject object = new JSONObject(s);
            if (!object.getBoolean("error")) {
                Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                Intent inn = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(inn);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        if (requestCode == CODE_POST_REQUEST)
            return requestHandler.sendPostRequest(url, params);

        if (requestCode == CODE_GET_REQUEST)
            return requestHandler.sendgGetRequest(url);
    }

}
