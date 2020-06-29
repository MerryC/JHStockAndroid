package com.example.jimshoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    EditText editText, editTextNama, editTextMerk, editTextJumlah;
    Spinner spinertempat;
    ProgressBar progressBar;
    ListView listView;
    Button buttonAddUpdate;

    List<Hero> heroList;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listViewHeroes);

        heroList = new ArrayList<>();

        readHeroes();
    }

    private void createHero(){
        String nama = editTextNama.getText().toString().trim();
        String merek = editTextMerk.getText().toString().trim();

        String jumlah = spinertempat.getSelectedItem().toString();

        if(TextUtils.isEmpty(nama)){
            editTextNama.setError("Please enter name");
            editTextNama.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(merek)){
            editTextMerk.setError("Please enter Merk");
            editTextMerk.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(jumlah)){
            editTextJumlah.setError("Please enter Jumlah");
            editTextJumlah.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("nama", nama);
        params.put("merek", merek);
        params.put("jumlah", jumlah);
        params.put("tempat", tempat);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_HERO, params, CODE_POST_REQUEST);
        request execute();

    }

    private void readHeroes(){
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_READ_HERO, params, CODE_GET_REQUEST);
    }
}
