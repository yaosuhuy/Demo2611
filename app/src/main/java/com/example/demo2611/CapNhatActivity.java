package com.example.demo2611;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CapNhatActivity extends AppCompatActivity {

    EditText txtMa, txtTenSP, txtSoLuong;
    Button btnSua;
    Context context;

    int soLuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat);

        btnSua = findViewById(R.id.uptBtn);
        txtMa = findViewById(R.id.maSPEdt);
        txtTenSP = findViewById(R.id.tenSPEdt);
        txtSoLuong = findViewById(R.id.soluongSPUdt);

        context = this;
        SanPham_DAO sanPham_dao = new SanPham_DAO(context);
        Intent intent = getIntent();

        String maSP = intent.getStringExtra("maSP");
        String tenSP = intent.getStringExtra("tenSP");
        int soLuongString = intent.getIntExtra("soLuong", 0);

        txtMa.setText(maSP);
        txtTenSP.setText(tenSP);
        txtSoLuong.setText(String.valueOf(soLuongString));

            btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham s = new SanPham(); // tao san pham chua du lieu nguoi dung nhap

                // dua du lieu vao doi tuong
                s.setMaSP(txtMa.getText().toString());
                s.setTenSP(txtTenSP.getText().toString());
                s.setSoLuong(Integer.parseInt(txtSoLuong.getText().toString()));

                // goi ham insert
                int kq = sanPham_dao.UpdateSanPham(s);
                if (kq == -1) {
                    Toast.makeText(context, "Sửa sản phẩm thất bại!", Toast.LENGTH_LONG).show();
                }
                if (kq == 1) {
                    Toast.makeText(context, "Sửa sản phẩm thành công!", Toast.LENGTH_LONG).show();
                    // Truyền dữ liệu sang MainActivity
                    Intent intent = new Intent(CapNhatActivity.this, MainActivity.class);
                    intent.putExtra("maSP", s.getMaSP());
                    intent.putExtra("tenSP", s.getTenSP());
                    intent.putExtra("soLuong", s.getSoLuong());
                    startActivity(intent);
                }
            }
        });
    }
}