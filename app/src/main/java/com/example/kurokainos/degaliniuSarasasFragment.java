package com.example.kurokainos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.postgresql.util.PSQLException;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.postgresql.util.PSQLException;



public class degaliniuSarasasFragment extends Fragment {
    private Spinner selectCity;
    private String Items;
    private ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



/*
        String url = "jdbc:postgresql://10.0.2.2:5432/maxima_db";
        String password = "1234";
        String user = "postgres";



        try {

            Class.forName("org.postgresql.Driver");
            String sql="SELECT \"id\", \"miestas\", \"pavadinimas\", \"adresas\", \"benzinoKaina\",\"dyzelioKaina\", \"dujuKaina\" FROM degalinesInfo WHERE \"miestas\" = 'Vilnius'";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection myConn = DriverManager.getConnection(url, user, password);
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery(sql);
            myRs.next();

            System.out.println(myRs.getString("miestas"));


            myConn.close();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
*/



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //PAIMA DABARTINE DATA
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = formatter.format(date);
        //UZDEDA DABARTINE DATA TEXTVIEW
        View v = inflater.inflate(R.layout.fragment_degaliniu_sarasas, container, false);
        TextView atnaujinta = (TextView) v.findViewById(R.id.atnaujinta);
        atnaujinta.setText(strDate);

        //uzdedam spinneriui miestus, is kuriu galima rinktis
        selectCity = (Spinner) v.findViewById(R.id.selectCity);
        String[] Item = new String[]{"ALL", "Vilnius", "Kaunas", "Siauliai", "Panevezys", "Klaipeda"};
        selectCity.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, Item));
        selectCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Items = selectCity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //IDEDAM LISTVIEW

        ArrayList<Degalines> arrayList = new ArrayList<>();

        arrayList.add(new Degalines(1, "Vilnius", "Circle K", "Talino g. 2B", "1.739", "1.839", "0.739"));
        arrayList.add(new Degalines(1, "Vilnius", "Circle K", "Laisvės pr. 43C", "1.689", "2.57", "1.38"));
        arrayList.add(new Degalines(2, "Kaunas", "Circle K", "Geležinio Vilko g. 2A", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(2, "Kaunas", "Circle K", "A.P.Kavoliuko g. 32A", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(2, "Kaunas", "Circle K", "Savanorių pr. 119A", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(1, "Vilnius", "Circle K", "A.Goštauto g. 13", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(2, "Kaunas", "Emsi", "Gel12321321321", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(1, "Vilnius", "Baltic Petrolium", "Gteststestests", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(1, "Vilnius", "test", "123456789g", "1.57", "2.57", "1.38"));
        arrayList.add(new Degalines(3, "Klaipeda", "tsetsts", "Gzzzzzzz", "1.57", "2.57", "1.38"));


        listView = (ListView) v.findViewById(R.id.listView);

        DegalinesAdaptor degalinesAdaptor = new DegalinesAdaptor(getActivity(), R.layout.listview_row_data,arrayList);

        listView.setAdapter(degalinesAdaptor);


        selectCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DegalinesInfoActivity.class);
                Degalines list = arrayList.get(position);
                intent.putExtra("degalinesPavadinimas", list.getPavadinimas());
                intent.putExtra("degalinesAdresas", list.getAdresas());
                intent.putExtra("benzinas", list.getBenzinoKaina());
                intent.putExtra("dyzelis", list.getDyzelioKaina());
                intent.putExtra("dujos", list.getDujuKaina());
                startActivity(intent);
            }
        });

        return v;
    }



}