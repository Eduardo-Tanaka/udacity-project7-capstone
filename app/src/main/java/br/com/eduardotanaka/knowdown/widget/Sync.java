package br.com.eduardotanaka.knowdown.widget;

import android.content.Context;
import android.content.Intent;

public class Sync {

    public static final String ACTION_DATA_UPDATED = "br.com.eduardotanaka.knowdown.ACTION_DATA_UPDATED";

    public static void start(Context context){
        Intent dataUpdatedIntent = new Intent(ACTION_DATA_UPDATED).setPackage(context.getPackageName());
        context.sendBroadcast(dataUpdatedIntent);
    }
}
