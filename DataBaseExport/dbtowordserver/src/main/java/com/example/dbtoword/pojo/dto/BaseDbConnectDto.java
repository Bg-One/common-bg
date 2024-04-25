package com.example.dbtoword.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author bo
 * @Date 2024 04 24 15 45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDbConnectDto {

    private String user;

    private String password;

    private String url;

    private String type;
}
