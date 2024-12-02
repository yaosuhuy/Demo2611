package com.example.demo2611;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    List<SanPham> listSanPham;
    int resource;

    SanPham_DAO sanPhamDAO;

    public SanPhamAdapter(@NonNull Activity context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listSanPham = objects;
        this.resource = resource;
        sanPhamDAO = new SanPham_DAO(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(resource, null);

        TextView maSP = view.findViewById(R.id.maSP);
        TextView tenSP = view.findViewById(R.id.tenSP);
        TextView soLuong = view.findViewById(R.id.soLuong);
        ImageButton optBtn = view.findViewById(R.id.optBtn);

        SanPham sanPham = this.getItem(position);
        maSP.setText(sanPham.getMaSP());
        tenSP.setText(sanPham.getTenSP());
        soLuong.setText(sanPham.getSoLuong() + "");

        optBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optBtn.setTag(position);
                showPopupMenu(optBtn);
            }
        });
        return view;
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_resource, popupMenu.getMenu());

        int position = (int) view.getTag();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_delete) {

                    SanPham sanPham = listSanPham.get(position);

                    int kq = sanPhamDAO.DeleteSanPham(sanPham.getMaSP());
                    if (kq == -1) {
                        Toast.makeText(context, "Xóa sản phẩm thất bại!", Toast.LENGTH_LONG).show();
                    }
                    if (kq == 1) {
                        listSanPham.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Xóa sản phẩm thành công!", Toast.LENGTH_LONG).show();
                    }
                    return true;
                } else if (item.getItemId() == R.id.menu_update) {
                    SanPham sanPham = listSanPham.get(position);
                    Intent i  = new Intent(context, CapNhatActivity.class);
                    // Truyền dữ liệu sản phẩm được chọn sang activity mới
                    i.putExtra("maSP", sanPham.getMaSP());
                    i.putExtra("tenSP", sanPham.getTenSP());
                    i.putExtra("soLuong", sanPham.getSoLuong());
                    Log.d("KQSL", String.valueOf(sanPham.getSoLuong()));
                    context.startActivity(i);
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}
