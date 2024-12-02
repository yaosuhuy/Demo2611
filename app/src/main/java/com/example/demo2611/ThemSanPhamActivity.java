package com.example.demo2611;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThemSanPhamActivity extends AppCompatActivity {

    EditText txtMa, txtTenSP, txtSoLuong;
    Button btnThem;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);

        btnThem = findViewById(R.id.addBtn);
        txtMa = findViewById(R.id.maSPEdt);
        txtTenSP = findViewById(R.id.tenSPEdt);
        txtSoLuong = findViewById(R.id.soluongSPEdt);

        context = this;
        SanPham_DAO sanPham_dao = new SanPham_DAO(context);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham s = new SanPham(); // tao san pham chua du lieu nguoi dung nhap

                // dua du lieu vao doi tuong
                s.setMaSP(txtMa.getText().toString());
                s.setTenSP(txtTenSP.getText().toString());
                s.setSoLuong(Integer.parseInt(txtSoLuong.getText().toString()));

                // goi ham insert
                boolean kq = sanPham_dao.insert(s);
                if (kq == false) {
                    Toast.makeText(context, "Thêm sản phẩm thất bại!", Toast.LENGTH_LONG).show();
                }
                else {
                    // Truyền dữ liệu sang MainActivity
                    Intent intent = new Intent(ThemSanPhamActivity.this, MainActivity.class);
                    intent.putExtra("maSP", s.getMaSP());
                    intent.putExtra("tenSP", s.getTenSP());
                    intent.putExtra("soLuong", s.getSoLuong());
                    startActivity(intent);
                    Toast.makeText(context, "Thêm sản phẩm thành công!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}