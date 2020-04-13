package com.example.broomlk;

import android.provider.BaseColumns;

public final class DriverProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DriverProfile() {}

    /* Inner class that defines the table contents */
    public static class Driver implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "dataOfBirth";
        public static final String COLUMN_3 = "password";
        public static final String COLUMN_4 = "nic";
        public static final String COLUMN_5 = "age";
        public static final String COLUMN_6 = "gender";
    }
}