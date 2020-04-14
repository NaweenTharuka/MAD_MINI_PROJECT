package com.example.broomlk;

import android.provider.BaseColumns;

public class ResProfile {
    private ResProfile(){}

    public static class Res implements BaseColumns {
        public static final String TABLE_NAME = "ReservationDet";
        public static final String COL_1 = "phonenumber";
        public static final String COL_2 = "pickupdate";
        public static final String COL_3 = "returndate";
        public static final String COL_4 = "modle";
        public static final String COL_5 = "submodle";

    }

}
