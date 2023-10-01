package com.yoshi.rain

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import androidx.core.util.SizeFCompat
import androidx.core.widget.updateAppWidget
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetProvider

/**
 * Implementation of App Widget functionality.
 */
class OreoWidget : HomeWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        widgetData: SharedPreferences
    ) {
        for (appWidgetId in appWidgetIds) {
            val supportedSizes = listOf(
                SizeFCompat(100.0f, 40.0f),
                SizeFCompat(230.0f, 40.0f),
            )
            appWidgetManager.updateAppWidget(appWidgetId, supportedSizes) {
                val layoutId = when (it) {
                    supportedSizes[0] -> R.layout.oreo_widget_small
                    else -> R.layout.oreo_widget_medium
                }
                RemoteViews(context.packageName, layoutId).apply {
                    val pendingIntent = HomeWidgetLaunchIntent.getActivity(
                    context,
                    MainActivity::class.java)
                    setOnClickPendingIntent(R.id.widget_day_weather, pendingIntent)

                    val image = widgetData.getString("weather_icon", null)
                    setImageViewBitmap(R.id.widget_day_icon, BitmapFactory.decodeFile(image))

                    val degree = widgetData.getString("weather_degree", null)
                    setTextViewText(R.id.widget_day_title, degree)
                }
            }
        }
    }
}