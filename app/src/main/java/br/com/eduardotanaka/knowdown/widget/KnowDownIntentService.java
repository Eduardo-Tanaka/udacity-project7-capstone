package br.com.eduardotanaka.knowdown.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.RemoteViews;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import br.com.eduardotanaka.knowdown.MainActivity;
import br.com.eduardotanaka.knowdown.R;
import br.com.eduardotanaka.knowdown.model.User;

public class KnowDownIntentService extends IntentService implements ValueEventListener {

    private User user;

    public KnowDownIntentService() {
        super("KnowDownIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        user = new User();
        user.retrieveIdSP(this);

        user.contextDataDB(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        user = dataSnapshot.getValue(User.class);

        // Retrieve all of the Today widget ids: these are the widgets we need to update
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                KnowDownWidgetProvider.class));

        // Perform this loop procedure for each Today widget
        for (int appWidgetId : appWidgetIds) {
            int layoutId = R.layout.know_down_widget_provider;
            RemoteViews views = new RemoteViews(getPackageName(), layoutId);

            views.setTextViewText(R.id.appwidget_text, user.getName());
            views.setTextViewText(R.id.widget_score, getResources().getString(R.string.txt_moeda) + ": " + String.valueOf(user.getCoin()));
            views.setTextViewText(R.id.widget_coin,  getResources().getString(R.string.txt_pontos) + ": " + String.valueOf(user.getScore()));

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
