package edu.neu.madcourse.numad22sp_azimtamboli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    /*
    I have borrowed this code from professors sample code which I found extremely helpful.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(ItemActivity.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    /*
        I have borrowed this code from professors sample code which I found extremely helpful.
         */
    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    /*
        I have borrowed this code from professors sample code which I found extremely helpful.
         */
    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String itemDesc = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");

                    ItemCard itemCard = new ItemCard(itemName, itemDesc);

                    itemList.add(itemCard);
                }
            }
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());
            // put itemUrl information into instance
            outState.putString(KEY_OF_INSTANCE + i + "2", itemList.get(i).getItemUrl());
        }
        super.onSaveInstanceState(outState);

    }
    /*
        I have borrowed this code from professors sample code which I found extremely helpful.
         */
    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                rviewAdapter.notifyItemChanged(position);
                Toast.makeText(ItemActivity.this, "Link Clicked-", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(position).getItemUrl().substring(6)));
                startActivity(intent);

            }

        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }

    /**
     * I have borrowed small piece of code to create custom dialouge
     * https://github.com/Pro-Grammerr/Custom-Dialog/blob/master/app/src/main/java/com/awesomeness/customdialog/MainActivity.java
     */
    private void showCustomDialog() {
        final Dialog dialog = new Dialog(ItemActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(true);

        dialog.setContentView(R.layout.dialog);

        final EditText nameEt = dialog.findViewById(R.id.name_d);
        final EditText urlEt = dialog.findViewById(R.id.url_d);
        Button submitButton = dialog.findViewById(R.id.submit);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                String url = urlEt.getText().toString();

                addItem(name, url);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void addItem(String name, String url) {

        if (URLUtil.isValidUrl(url) && (name != null || name.length()!=0)) {
            itemList.add(0, new ItemCard("Name : " + name,"Url : " + url));

            Snackbar.make(findViewById(R.id.recyclerView), "Added link successfully",
                    Snackbar.LENGTH_SHORT)
                    .show();

            rviewAdapter.notifyItemInserted(0);
        } else {
            Toast.makeText(ItemActivity.this, "Invalid URL/name", Toast.LENGTH_SHORT).show();
        }


    }
}