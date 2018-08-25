package com.example.umaaamm.dewi_jilbabadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by umaaamm on 15/07/18.
 */

public class Tentang extends AppCompatActivity {
    //private ExpandableListAdapter listAdapter;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang_activiry);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("Versi");
        listDataHeader.add("Pengembang");
        listDataHeader.add("Hubungi Kami");
        List<String> versi = new ArrayList<String>();
        versi.add("v1.0");

        List<String> developer = new ArrayList<String>();
        developer.add("Dewi Engga Tau nama nya anak DJ");

        List<String> contact = new ArrayList<String>();
        contact.add("Email : JilbabKu@gmail.com");

        listDataChild.put(listDataHeader.get(0), versi);
        listDataChild.put(listDataHeader.get(1), developer);
        listDataChild.put(listDataHeader.get(2), contact);
    }
}


