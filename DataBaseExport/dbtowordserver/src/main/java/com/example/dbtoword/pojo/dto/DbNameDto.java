package com.example.dbtoword.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author bo
 * @Date 2024 04 24 21 43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbNameDto extends BaseDbConnectDto {
    private String dbName;
}
