package com.example.broomlk;

import android.provider.BaseColumns;

public final class UserProfile {

    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "userFName";
        public static final String COLUMN_3 = "userLName";
        public static final String COLUMN_4 = "userEmail";
        public static final String COLUMN_5 = "phone";
        public static final String COLUMN_6 = "password";
        public static final String COLUMN_7 = "city";

    }

}
