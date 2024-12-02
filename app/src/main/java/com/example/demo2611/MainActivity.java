package com.example.demo2611;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewSanPham;
    List<SanPham>listSanPham = new ArrayList<>();
    SanPhamAdapter adapter;

    Button btnThem;

    Context context;

    EditText searchEdt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEdt = findViewById(R.id.searchEdt);
        context = this;

        // Nhận dữ liệu từ Intent
//        Intent intent = getIntent();
//        String maSP = intent.getStringExtra("maSP");
//        String tenSP = intent.getStringExtra("tenSP");
//        int soLuong = intent.getIntExtra("soLuong", 0);

        listSanPham.clear();
        SanPham_DAO sanPham_dao = new SanPham_DAO(context);
        listSanPham = sanPham_dao.getAllSanPham();

        adapter  = new SanPhamAdapter(MainActivity.this, R.layout.sanpham, listSanPham);
        listViewSanPham = findViewById(R.id.list_item);
        listViewSanPham.setAdapter(adapter);

        btnThem  = findViewById(R.id.button);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ThemSanPhamActivity.class);
                startActivity(i);
            }
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // không cần xử lý trước khi văn bản thay đổi

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // không cần xử lý khi văn bản thay đổi

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Khi văn bản thay đổi, lọc dữ liệu và hiển thị lại ListView
                timKiemSanPham(editable.toString());
            }

            private void timKiemSanPham(String str){
                List<SanPham> filteredList = sanPham_dao.getFilteredSanPhamToString(str);
                adapter.clear();
                adapter.addAll(filteredList);
                adapter.notifyDataSetChanged();
            }
        });



    }


}