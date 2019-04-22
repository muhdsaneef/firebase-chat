package com.dailyapps.scribbles.utils;

import com.dailyapps.scribbles.app.AppConstants;

public class AppUtils {

    public static boolean validateChatRoomName(String chatRoomName) {

        return chatRoomName.trim().matches("[a-zA-Z ]+");
    }

    public static String getFormattedTitle(String title) {
        return title.toLowerCase().replace(AppConstants.SPACE, AppConstants.EMPTY);
    }
}
