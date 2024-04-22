package com.example.gestionecomanda.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestUtil {

    public static String formattedTimestamp(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(timestamp);
    }

}
