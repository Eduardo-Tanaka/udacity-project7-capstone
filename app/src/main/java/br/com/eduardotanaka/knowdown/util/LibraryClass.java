package br.com.eduardotanaka.knowdown.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class LibraryClass {

    public static String PREF = "br.com.eduardo.firebase.PREF";
    private static DatabaseReference firebase;


    private LibraryClass(){}

    public static DatabaseReference getFirebase(){
        if( firebase == null ){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            firebase = FirebaseDatabase.getInstance().getReference();
        }

        return( firebase );
    }

    static public void saveSP(Context context, String key, String value ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    static public String getSP(Context context, String key ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String token = sp.getString(key, "");
        return( token );
    }
}
