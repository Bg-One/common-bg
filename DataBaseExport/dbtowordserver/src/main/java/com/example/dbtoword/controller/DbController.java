package com.example.dbtoword.controller;

import com.example.dbtoword.pojo.dto.BaseDbConnectDto;
import com.example.dbtoword.pojo.dto.DbDocumentDto;
import com.example.dbtoword.pojo.dto.DbNameDto;
import com.example.dbtoword.response.CommonResult;
import com.example.dbtoword.response.CommonResultEnum;
import com.example.dbtoword.service.IDbService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author bo
 * @Date 2024 04 24 15 38
 **/
@RestController
@RequestMapping("db")
public class DbController {

    @Resource
    private IDbService iDbService;

    /**
     * 连接数据库
     */
    @PostMapping("connect")
    public CommonResult connectDb( BaseDbConnectDto baseDbConnectDto) {
        iDbService.connectDb(baseDbConnectDto);
        return new CommonResult(CommonResultEnum.SUCCESS,"成功");
    }


    /**
     * 获取数据库表格信息
     */
    @PostMapping("listDbTable")
    public CommonResult listDbTable( DbNameDto dbNameDto) {
        Object o = iDbService.listDbTable(dbNameDto);
        if (o == null) {
            return new CommonResult(CommonResultEnum.DB_CONNECT_ERROR, "数据库连接异常");
        }
        return new CommonResult(CommonResultEnum.SUCCESS, o);
    }


    /**
     * 导出表格
     */
    @PostMapping("exportDbTable")
    public CommonResult exportDbTable( DbDocumentDto dbDocumentDto) {
        iDbService.exportDbTable(dbDocumentDto);
        return new CommonResult(CommonResultEnum.SUCCESS, "成功");
    }


}
