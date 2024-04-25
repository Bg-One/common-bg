package com.example.dbtoword.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author bo
 * @Date 2024 04 25 09 30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbDocumentDto extends BaseDbConnectDto{

    private String fileOutputDir;

    private String fileType;

    private String fileName;

    private String description;

    private String version;

    private String ignoreTableName;

    private String checkTableName;
}
