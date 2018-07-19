package com.example.beyza.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class  MainActivity extends AppCompatActivity {

    private ListView VeriListele;
    Veritabani db;
    //int idBul = 0;
    String idBul,etAd, etSoyad,etTel,etCinsiyet;
    ArrayAdapter<String> adapter;
    Context context=this;
    List<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VeriListele = (ListView) findViewById(R.id.VeriListele);
        Listele();
        ListViewItem();

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }
    public void Listele()
    {
        Veritabani vt=new Veritabani(MainActivity.this);
        List<String> list=vt.VeriListele();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,list);
        VeriListele.setAdapter(adapter);
    }

    public void ListViewItem()
    {
        VeriListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //bilgileriCek(VeriListele.getItemAtPosition(i).toString()); //tıklanan veriyi alıp bilgileri çek'e gönderdik

                // Tıklanan verimizi alıyoruz
                String item = VeriListele.getItemAtPosition(i).toString();
                // - Göre bölüyoruz
                String[] itemBol = item.split(" - ");
                // id'mizi alıyoruz
                idBul = String.valueOf(itemBol[0].toString());
                etAd=String.valueOf(itemBol[1].toString());
                etSoyad=String.valueOf(itemBol[2].toString());
                //etTel=String.valueOf(itemBol[3].toString());
                //etCinsiyet=String.valueOf(itemBol[4].toString());

                // Diğer verilerimizi set ediyoruz.
                // etAd.setText(itemBol[1].toString());
                //etSoyad.setText(itemBol[2].toString());
                //etTel.setText(itemBol[3].toString());
                //etCinsiyet.setText(itemBol[4].toString());
                bilgileriCek(etAd);

            }
        });
    }

    public void bilgileriCek(String kisiAd)
    {
        try
        {

            //adapter=new ArrayAdapter<>(context,android.R.layout.select_dialog_singlechoice);
            ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_singlechoice);


            Veritabani vt=new Veritabani(MainActivity.this);
            String kisiAdi=vt.VeriGetir(kisiAd);
            adapter.add(kisiAdi + " \n " + null + " \n " + null + " \n " + null);
//
//            String[] sutunlar={" ROW_ID ", " ROW_AD ", " ROW_SOYAD ", " ROW_TEL ", " ROW_CINSIYET "};
//            db = new Veritabani(MainActivity.this);
//            SQLiteDatabase sdb=db.getReadableDatabase();
//
//
//            Cursor okunanlar=sdb.query("TABLO_KISILER",sutunlar, "ROW_AD=?",new String[]{kisiAd},null,null,null,null);
//            //ad a göre arama yapıldı. 3. parametre where
            //4.parametre where yerine koyacağımız parametre

//            String ad="";
//            String soyad="";
//            String tel="";
//            String cinsiyet="";
//
//            while(okunanlar.moveToNext())
//            {
//                ad=okunanlar.getString(okunanlar.getColumnIndex("ROW_AD"));
//                soyad=okunanlar.getString(okunanlar.getColumnIndex("ROW_SOYAD"));
//                tel=okunanlar.getString(okunanlar.getColumnIndex("ROW_TEL"));
//                cinsiyet=okunanlar.getString(okunanlar.getColumnIndex("ROW_CINSIYET"));
//
//                //arrayAdapter2.add(ad + " \n " + soyad + " \n " + tel + " \n " + cinsiyet);
//                adapter.add(ad + " \n " + soyad + " \n " + tel + " \n " + cinsiyet);
//
//            }
            //cekilenleriGoster(arrayAdapter2);
            cekilenleriGoster(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Bilgiler getirilirken hata oluştu",Toast.LENGTH_SHORT).show();
        }
    }

    private void cekilenleriGoster(final ArrayAdapter arrayAdapter2)
    {
        AlertDialog.Builder builderSingle=new AlertDialog.Builder(MainActivity.this);
        builderSingle.setIcon(R.drawable.ic_launcher_background);
        builderSingle.setTitle("Getirilen Kişi");

        builderSingle.setNegativeButton("Çıkış", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String strName=(String)arrayAdapter2.getItem(i);
                AlertDialog.Builder builderInner=new AlertDialog.Builder(MainActivity.this);
                builderInner.setCancelable(false);
                builderInner.setMessage(strName);

                builderInner.setTitle("Kayıtlar");
                builderInner.setPositiveButton("Değiştir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builderInner.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }
}
