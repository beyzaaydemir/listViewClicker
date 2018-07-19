package com.example.beyza.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "musteriler";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLO_KISILER = "kisiler";
    private static final String ROW_ID = "id";
    private static final String ROW_AD = "ad";
    private static final String ROW_SOYAD = "soyad";
    private static final String ROW_TEL = "tel";
    private static final String ROW_CINSIYET="cinsiyet";




    public Veritabani(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_KISILER + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_AD + " TEXT NOT NULL, "
                + ROW_SOYAD + " TEXT NOT NULL, "
                + ROW_TEL + " TEXT NOT NULL, "
                + ROW_CINSIYET + " TEXT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    public void VeriEkle(String ad,String soyad, String tel,String cinsiyet)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(ROW_AD,ad);
            cv.put(ROW_SOYAD,soyad);
            cv.put(ROW_TEL,tel);
            cv.put(ROW_CINSIYET,cinsiyet);
            db.insert(TABLO_KISILER, null,cv);
        }catch (Exception e)
        {

        }
        db.close();
    }

    public List<String> VeriListele()
    {
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=getReadableDatabase();

        try
        {
            String[] stunlar={ROW_ID, ROW_AD, ROW_SOYAD};
            Cursor cursor=db.query(TABLO_KISILER,stunlar,null,null,null,null,null);
            while(cursor.moveToNext())
            {


                veriler.add(cursor.getInt(0)
                        + " -         "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2));
                /*+ " - "
                +cursor.getString(3)
                + " - "
                + cursor.getString(4));*/
            }
        }catch (Exception e)
        {

        }
        db.close();
        return veriler;

    }

    /// id ile kişi döner...
    public String VeriGetir(String name)
    {

        SQLiteDatabase db=getReadableDatabase();
        String[] sutunlar={ROW_ID, ROW_AD, ROW_SOYAD};
        Cursor okunanlar=db.query(TABLO_KISILER,sutunlar, "ad=?",new String[]{name},null,null,null,null);
        while(okunanlar.moveToNext())
        {
            return  okunanlar.getString((0));
        }
        return null;

    }


   /* public Kisiler VeriGetir(Kisiler kisi)
    {
        SQLiteDatabase db=getReadableDatabase();
        String[] sutunlar={ROW_ID, ROW_AD, ROW_SOYAD, ROW_TEL, ROW_CINSIYET};
        Cursor okunan=db.query(TABLO_KISILER,sutunlar, "ad=?",new String[] {String.valueOf(kisi)},null,null,null,null);
        while (okunan.moveToNext())
        {
            return (Kisiler) okunan;
        }
        return null;
    }*/
}
