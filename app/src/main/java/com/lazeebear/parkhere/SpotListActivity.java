package com.lazeebear.parkhere;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lazeebear.parkhere.dummy.DummyContent;

import java.util.List;

/**
 * An activity representing a list of Spots. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SpotDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SpotListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_list);
        Intent intent = getIntent();
        for (int i=0; i<5; i++) {
            String add = intent.getStringExtra("address"+i);
            populateList(add);
        }
    }

    private void populateList(String address) {
        //address + : + id
        String[] arr = address.split(":");
        LinearLayout list = (LinearLayout) findViewById(R.id.spotList);
        Button spotButton = createSpotButton(arr[0]);
        spotButton.setId(Integer.parseInt(arr[1]));
        list.addView(spotButton);
    }

    private Button createSpotButton(final String address) {
        final Button button = new Button(this);
        button.setText(address);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //send to spot detail page
                goToSpotDetail(button.getId());
            }
        });

        return button;
    }

    private void goToSpotDetail(int id){
        Intent intent = new Intent(this, SpotDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }


}