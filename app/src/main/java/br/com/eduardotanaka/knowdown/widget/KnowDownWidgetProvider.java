package br.com.eduardotanaka.knowdown.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import br.com.eduardotanaka.knowdown.MainActivity;
import br.com.eduardotanaka.knowdown.R;

/**
 * Implementation of App Widget functionality.
 */
public class KnowDownWidgetProvider extends AppWidgetProvider {

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {
        context.startService(new Intent(context, KnowDownIntentService.class));
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, KnowDownIntentService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (Sync.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            context.startService(new Intent(context, KnowDownIntentService.class));
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

