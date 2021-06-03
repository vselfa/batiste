package com.example.batiste;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends MainMenu {
    // Llista productes. La passem com paràmetre del setAdapter
    List<Producte> llistaProductes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        // La ListViww que mostra els productes
        ListView listViewProductes = findViewById(android.R.id.list);
        llistaProductes = new ArrayList<>(30);
        Producte producte;
        //R.drawable es un int
        producte = new Producte("Paella marisc", "La típica paella valenciana de marisc",  R.drawable.plat_paella_marisc);
        llistaProductes.add(producte);
        producte =  new Producte("Fideuà", "Fideuà peix amb fideus fins", R.drawable.plat_fideua);
        llistaProductes.add(producte);
        producte = new Producte("Truita de creïlles", "Amb creïlles i sense ceba",  R.drawable.plat_tortilla_creilles);
        llistaProductes.add(producte);

        listViewProductes.setAdapter(new CustomArrayAdapter(this, llistaProductes));
    }
}