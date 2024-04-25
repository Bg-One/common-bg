package com.example.dbtoword.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author bo
 * @Date 2024 04 25 14 14
 **/
public class DbDriverClassName {

   public final static Map<String,String> DRIVER_MAP =new HashMap<String,String>();

    static {
        DRIVER_MAP.put("mysql","com.mysql.jdbc.Driver");
        DRIVER_MAP.put("oracle","oracle.jdbc.driver.OracleDriver");
        DRIVER_MAP.put("sqlserver","com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
}
