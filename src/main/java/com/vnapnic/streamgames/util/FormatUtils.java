package com.vnapnic.streamgames.util;

import android.text.format.DateFormat;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import com.vnapnic.streamgames.TDApplication;

public class FormatUtils {

    private static final String EMPTY_GAME_PLACEHOLDER = "-";

    public static String formateDate(Date date) {
        StringBuilder result = new StringBuilder();
        result.append(DateFormat.getDateFormat(TDApplication.getContext()).format(date));
        result.append(" ");
        result.append(DateFormat.getTimeFormat(TDApplication.getContext()).format(date));
        return result.toString();
    }

    public static String formatTime(long duration) {
        long hours = duration / 3600;
        long minutes = (duration % 3600) / 60;
        long seconds = duration % 60;

        if (hours > 0) {
            return String.format(Locale.ENGLISH, "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.ENGLISH, "%d:%02d", minutes, seconds);
        }
    }

    public static String formatNumber(long number) {
        return NumberFormat.getInstance().format(number);
    }

    public static String formatGame(String game) {
        return StringUtils.isEmpty(game) ? EMPTY_GAME_PLACEHOLDER : game;
    }
}