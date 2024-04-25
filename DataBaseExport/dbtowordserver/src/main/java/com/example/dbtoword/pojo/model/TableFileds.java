package com.example.dbtoword.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author bo
 * @Date 2024 04 24 17 24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableFileds {
    private  String enName;

  private  String cnName;

    private  String type;

    private  String nullFlag;

    private  String keyFlag;

    private  String defaultName;

}
