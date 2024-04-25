package com.example.dbtoword.untils;

import com.example.dbtoword.exception.CustomException;
import com.example.dbtoword.response.CommonFun;
import com.example.dbtoword.response.CommonResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author liuzhaobo
 */
@Slf4j
@Component
public class ConnectUtil {


    /**
     * 设置是否自动提交事务
     * 当需要进行批量带事务的操作时，关闭自动提交手动管理事务，将会大大提高效率！
     */
    public void setAutoCommit(Connection connection,boolean autoCommit){
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            CommonFun.outputException(e,log);
        }
    }

    /**
     * 关闭自动提交事务时，需要手动管理事务提交、回滚
     */
    public void commit(Connection connection){
        try {
            connection.commit();
        } catch (SQLException e) {
            CommonFun.outputException(e,log);
        }
    }
    public void rollback(Connection connection){
        try {
           connection.rollback();
        } catch (SQLException e) {
            CommonFun.outputException(e,log);
        }
    }

    /**
     * 关闭 Connection 连接
     */
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("关闭Connection连接异常...");
            CommonFun.outputException(e, log);
        }
    }

    /**
     * 查询
     * 查询语句
     */
    public ArrayList<HashMap<String,Object>> find(Connection conn,String sql, Object[] params) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        //获取连接
        PreparedStatement ps;
        ResultSet rs;

        try {
            //设置SQL、以及参数
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            //执行查询
            rs = ps.executeQuery();

            //获取查询结果
            ResultSetMetaData rm = rs.getMetaData();
            int columnCount = rm.getColumnCount();

            //封装结果集
            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    String name = rm.getColumnName(i).toLowerCase();
                    Object value = rs.getObject(i);

                    map.put(name,value);
                }
                list.add(map);
            }

        } catch (Exception e) {
            throw new CustomException(CommonResultEnum.DB_FIND_ERROR);
        }

        return list;
    }
    public HashMap<String,Object> findOne(Connection connection,String sql, Object[] params){
        ArrayList<HashMap<String, Object>> list = this.find(connection,sql, params);
        return list.size() > 0 ? list.get(0) : null;
    }
    public ArrayList<HashMap<String,Object>> find(Connection connection,String sql) {
        return this.find(connection,sql,null);
    }
    public HashMap<String,Object> findOne(Connection connection,String sql) {
        return this.findOne(connection,null);
    }

    /**
     * 执行
     * 新增/删除/更新 等SQL语句
     */
    public boolean execute(Connection conn,String sql, Object[] params){
        boolean flag = false;

        PreparedStatement ps;

        try {
            //设置SQL、以及参数
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            //执行
            //如果第一个结果是ResultSet 象，则返回 true；
            //如果第一个结果是更新计数或者没有结果，则返回 false；
            flag = ps.execute();
        } catch (SQLException e) {
            log.error("执行 jdbcUtil.update() 异常...");
            CommonFun.outputException(e,log);
        }

        return flag;
    }
    public boolean execute(Connection connection,String sql){
        return this.execute(connection,sql,null);
    }
}
