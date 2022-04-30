package com.example.kurokainos.singleDegaline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kurokainos.CommentListActivity;
import com.example.kurokainos.R;
import com.example.kurokainos.singleDegaline.MapsFragment;

public class DegalinesInfoActivity extends AppCompatActivity {
    TextView degalinesPavadinimas;
     TextView degalinesAdresas;
    TextView benzinas;
    TextView dyzelis;
    TextView dujos;
    Button commentListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_degalines_info);

        degalinesPavadinimas = (TextView) findViewById(R.id.degalinesPavadinimas);
        degalinesAdresas = (TextView) findViewById(R.id.degalinesAdresas);
        benzinas = (TextView) findViewById(R.id.benzinas);
        dyzelis = (TextView) findViewById(R.id.dyzelis);
        dujos = (TextView) findViewById(R.id.dujos);


        Intent intent = getIntent();






        double defaultValue=0.00;
        String degPavadinimas = intent.getStringExtra("degalinesPavadinimas");
        String degAdresas = intent.getStringExtra("degalinesAdresas");

        degalinesPavadinimas.setText(degPavadinimas);
        degalinesAdresas.setText(degAdresas);


        benzinas.setText(intent.getStringExtra("benzinas"));
        dyzelis.setText(intent.getStringExtra("dyzelis"));
        dujos.setText(intent.getStringExtra("dujos"));
        double lat =intent.getDoubleExtra("latitude",defaultValue);
        double longt =intent.getDoubleExtra("longtitude",defaultValue);
        Fragment map = new MapsFragment(longt,lat,degPavadinimas);
        getSupportFragmentManager().beginTransaction().replace(R.id.mapView, map).commit();



        commentListButton = findViewById(R.id.commentListButton);
        commentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCommentListActivity();
            }

            private void openCommentListActivity() {
                Intent intent = new Intent(getApplicationContext(), CommentListActivity.class);


                intent.putExtra("degalinesPavadinimas",degPavadinimas);
                intent.putExtra("degalinesAdresas",degAdresas);

                startActivity(intent);
            }
        });


    }
}