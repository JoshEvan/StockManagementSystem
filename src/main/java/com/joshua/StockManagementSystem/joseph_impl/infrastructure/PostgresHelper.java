package com.joshua.StockManagementSystem.joseph_impl.infrastructure;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.JosephDataEntity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresHelper {

  public static final String SUCCESS = " SUCCESSFULLY ";
  public static final String FAIL = " FAILED TO BE";
  public static final String INSERTED = " INSERTED ";
  public static final String UPDATED = " UPDATED ";
  public static final String REMOVED = " REMOVED ";
  public static final String ITEM = " ITEM ";
  public static final String TRANHEAD = " TRANSACTION ";

  public static String insertOperation(JosephDataEntity dataEntity){
    String sql = "INSERT INTO "
            + dataEntity.TABLE
            + " VALUES ( ";
//    for(int i = 0;i<dataEntity.numColumns;i++){
//      if(i>0) sql+=", ";
//      sql+="?";
//    }
    int j = 0;
    for (Field field : dataEntity.getClass().getDeclaredFields()) {
      //field.setAccessible(true); // if you want to modify private fields
      if(j >= dataEntity.numColumns) break;
      System.out.println(field.getName()
              + " - " + field.getType());
      if(j > 0) sql+=", ";
      if(field.getType() == Date.class){
        sql+="?::date";
      }else sql+="?";

      j++;
    }
    sql+=" )";
    return sql;
  }

  public static String selectOperation(JosephDataEntity dataEntity){
    String sql = "SELECT * FROM "
            + dataEntity.TABLE;
    return sql;
  }

  public static String updateOperation(JosephDataEntity dataEntity, HashMap<String,Object> params, String comparator){
    String sql = "UPDATE "
            + dataEntity.TABLE
            + " SET";
    int i = 0;
    for(Map.Entry param : params.entrySet()){
      if(i++ > 0)  sql+=",";
      sql+=" "+param.getKey()+" = \'"+param.getValue()+"\'";
    }
    sql+=" WHERE "+comparator;
    return sql;
  }


  public static String deleteOperation(JosephDataEntity dataEntity, String condition){
    return "DELETE FROM "+dataEntity.TABLE+ " "+condition;
  }

}
