package com.example.dbtoword.service;

import com.example.dbtoword.pojo.dto.BaseDbConnectDto;
import com.example.dbtoword.pojo.dto.DbDocumentDto;
import com.example.dbtoword.pojo.dto.DbNameDto;
import com.example.dbtoword.pojo.model.Tables;


import java.util.List;

/**
 * @Author bo
 * @Date 2024 04 24 16 56
 **/
public interface IDbService {

   void connectDb(BaseDbConnectDto baseDbConnectDto);

    List<Tables> listDbTable( DbNameDto dbNameDto);

    void exportDbTable(DbDocumentDto dbDocumentDto);
}
