package com.example.dbtoword.service.impl;

import com.example.dbtoword.exception.CustomException;
import com.example.dbtoword.pojo.dto.BaseDbConnectDto;
import com.example.dbtoword.pojo.dto.DbDocumentDto;
import com.example.dbtoword.pojo.dto.DbNameDto;
import com.example.dbtoword.pojo.model.Tables;
import com.example.dbtoword.response.CommonResultEnum;
import com.example.dbtoword.service.IDbService;
import com.example.dbtoword.untils.ConnectUtil;
import com.example.dbtoword.untils.GenerateDocumentUntil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author bo
 * @Date 2024 04 24 16 56
 **/
@Service
public class IDbServiceImpl implements IDbService {

    @Resource
    private GenerateDocumentUntil generateDocumentUntil;
    @Resource
    private ConnectUtil connectUtil;
    @Override
    public void connectDb(BaseDbConnectDto baseDbConnectDto) {
        DataSource dataSource = generateDocumentUntil.getDataSource(baseDbConnectDto);
        try {
            Connection connection = dataSource.getConnection();
            connectUtil.close(connection);
        } catch (SQLException e) {
            throw new CustomException(CommonResultEnum.DB_CONNECT_ERROR);
        }
    }

    @Override
    public List<Tables> listDbTable( DbNameDto dbNameDto) {
        DataSource dataSource = generateDocumentUntil.getDataSource(dbNameDto);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new CustomException(CommonResultEnum.DB_CONNECT_ERROR);
        }
        String dbName = dbNameDto.getDbName();
        String sql=String.format("select table_name as name,table_comment as comment from information_schema.tables where table_schema ='%s' order by table_name",dbName);
        ArrayList<HashMap<String, Object>> result = connectUtil.find(connection,sql);
        ArrayList<Tables>tables = new ArrayList<>();
        result.forEach(item->{tables.add(new Tables(item.get("name").toString(),item.get("comment").toString()));});
        connectUtil.close(connection);
        return tables;
    }


    @Override
    public void exportDbTable(DbDocumentDto dbDocumentDto) {
        generateDocumentUntil.documentGeneration(dbDocumentDto);
    }

}
