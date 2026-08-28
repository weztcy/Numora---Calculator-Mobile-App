package com.example.kalkulator;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<history> mExampleList;
    private RecyclerView mRecyclerView;
    private adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RadioGroup group_hitung;
    RadioButton tambah, kurang, kali, bagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonClear = findViewById(R.id.clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        group_hitung = findViewById(R.id.group_hitung);
        tambah = findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        kali = findViewById(R.id.kali);
        bagi = findViewById(R.id.bagi);

        group_hitung.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (tambah.isChecked()) {
                    Toast.makeText(MainActivity.this, "Tambah", Toast.LENGTH_SHORT).show();
                } else if (kurang.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kurang", Toast.LENGTH_SHORT).show();
                } else if (kali.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kali", Toast.LENGTH_SHORT).show();
                } else if (bagi.isChecked()) {
                    Toast.makeText(MainActivity.this, "Bagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<history>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new adapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton() {
        Button buttonCount = findViewById(R.id.count);
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText line1 = findViewById(R.id.input1);
                EditText line2 = findViewById(R.id.input2);
                insertItem(line1.getText().toString(), line2.getText().toString());
                saveData();
            }
        });
    }

    private void insertItem(String line1, String line2) {
        int hasil = 0;
        if (tambah.isChecked()) {
            hasil = Integer.parseInt(line1) + Integer.parseInt(line2);
        } else if (kurang.isChecked()) {
            hasil = Integer.parseInt(line1) - Integer.parseInt(line2);
        } else if (kali.isChecked()) {
            hasil = Integer.parseInt(line1) * Integer.parseInt(line2);
        } else if (bagi.isChecked()) {
            hasil = Integer.parseInt(line1) / Integer.parseInt(line2);
        }

        // memunculkan operasi yang dipilih dari group_hitung ke dalam hasil
        String operasi = "";
        if (tambah.isChecked()) {
            operasi = "+";
        } else if (kurang.isChecked()) {
            operasi = "-";
        } else if (kali.isChecked()) {
            operasi = "x";
        } else if (bagi.isChecked()) {
            operasi = ":";
        }

        mExampleList.add(new history(line1, operasi, line2, String.valueOf(hasil)));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mExampleList.clear();
        mAdapter.notifyDataSetChanged();
    }
}