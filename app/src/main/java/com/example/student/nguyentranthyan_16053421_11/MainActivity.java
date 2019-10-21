package com.example.student.nguyentranthyan_16053421_11;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_id, et_title, et_author;
    Button bt_save, bt_select, bt_update, bt_delete;
    GridView gv_display;
    DBhelpper dbHelper;
    Dialog dialog;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_thongtinsach:
                dialog = new Dialog(MainActivity.this);
                dialog.setTitle("thông tin sách");
                dialog.setContentView(R.layout.activity_main2);
                initView();
                dialog.show();
                Window window=dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                eventClick();
        }
        return super.onOptionsItemSelected(item);
    }
    private void initView() {
        et_id = (EditText) dialog.findViewById(R.id.editTextID);
        et_title = (EditText) dialog.findViewById(R.id.editTextTitle);
        et_author = (EditText) dialog.findViewById(R.id.editTextIdauthor);
        gv_display = (GridView) dialog.findViewById(R.id.gridView_listItem);
        dbHelper = new DBhelpper(this);
        bt_save = (Button) dialog.findViewById(R.id.buttonSave);
        bt_select = (Button) dialog.findViewById(R.id.buttonSelect);
        bt_delete = (Button) dialog.findViewById(R.id.buttonDelete);
        bt_update = (Button) dialog.findViewById(R.id.buttonUpdate);
    }
    private void eventClick() {
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId(Integer.parseInt(et_id.getText().toString()));
                book.setTitle(et_title.getText().toString());
                book.setIdAuthor(et_author.getText().toString());
                if (dbHelper.insertBook(book)) {
                    Toast.makeText(getApplicationContext(), "da luu thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "khong luu thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> booklist = new ArrayList<>();
                String id = et_id.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    Book book = dbHelper.getBook(idkq);
                    list.add(book.getId() + "");
                    list.add(book.getTitle());
                    list.add(book.getIdAuthor());
                } else {
                    booklist = dbHelper.getALlBook();
                    for (Book b : booklist) {
                        list.add(b.getId() + "");
                        list.add(b.getTitle());
                        list.add(b.getIdAuthor());
                    }
                }
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gv_display.setAdapter(adapter);
            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_id.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    dbHelper.deleteBook(idkq);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}



