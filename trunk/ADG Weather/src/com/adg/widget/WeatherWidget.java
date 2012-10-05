package com.adg.widget;

import com.adg.weather.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

public class WeatherWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
        RemoteViews remoteViews;
        ComponentName weatherWidget;
        
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        weatherWidget = new ComponentName( context, WeatherWidget.class );
        remoteViews.setImageViewBitmap(R.id.w_ImageCondition, null );
        remoteViews.setTextViewText( R.id.W_TextTemp, "Temp");
        remoteViews.setTextViewText( R.id.w_TextCity, "Boston");
        remoteViews.setTextViewText( R.id.w_TextCondition, "Sunny");
        remoteViews.setTextViewText( R.id.w_TextMax, "67");
        remoteViews.setTextViewText( R.id.w_TextMin, "43");
        appWidgetManager.updateAppWidget( weatherWidget, remoteViews );
	}

}
