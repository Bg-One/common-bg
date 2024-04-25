package com.example.dbtoword.untils;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.example.dbtoword.constant.DbDriverClassName;
import com.example.dbtoword.pojo.dto.BaseDbConnectDto;
import com.example.dbtoword.pojo.dto.DbDocumentDto;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author liuzhaobo
 */
@Component
public class GenerateDocumentUntil {

	public void documentGeneration(DbDocumentDto dbDocumentDto) {
		//数据源
		DataSource dataSource = getDataSource(dbDocumentDto);
		String fileType = dbDocumentDto.getFileType();
		//生成配置
		EngineConfig engineConfig = EngineConfig.builder()
				//生成文件路径
				.fileOutputDir(dbDocumentDto.getFileOutputDir())
				//打开目录
				.openOutputDir(true)
				//文件类型
				.fileType(fileType.equals("word")? EngineFileType.WORD:fileType.equals("html")? EngineFileType.HTML:EngineFileType.MD)
				//生成模板实现
				.produceType(EngineTemplateType.freemarker)
				//自定义文件名称
				.fileName(dbDocumentDto.getFileName()).build();

		ProcessConfig processConfig = ProcessConfig.builder()
				//指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
				//根据名称指定表生成
				.designatedTableName(dbDocumentDto.getCheckTableName().equals("")?new ArrayList<>():Arrays.asList(dbDocumentDto.getCheckTableName().split(",")))
				//忽略表名
				.ignoreTableName(dbDocumentDto.getIgnoreTableName().equals("")?new ArrayList<>(): Arrays.asList(dbDocumentDto.getIgnoreTableName().split(","))).build();
		//配置
		Configuration config = Configuration.builder()
				//版本
				.version(dbDocumentDto.getVersion())
				//描述
				.description(dbDocumentDto.getDescription())
				//数据源
				.dataSource(dataSource)
				//生成配置
				.engineConfig(engineConfig)
				//生成配置
				.produceConfig(processConfig)
				.build();

		//执行生成
		new DocumentationExecute(config).execute();
	}

	public DataSource getDataSource(BaseDbConnectDto baseDbConnectDto) {
		//数据源
		HikariConfig hikariConfig = new HikariConfig();
		String driverType = DbDriverClassName.DRIVER_MAP.get(baseDbConnectDto.getType());
		hikariConfig.setDriverClassName(driverType);
		hikariConfig.setJdbcUrl(baseDbConnectDto.getUrl());
		hikariConfig.setUsername(baseDbConnectDto.getUser());
		hikariConfig.setPassword(baseDbConnectDto.getPassword());

		//设置可以获取tables remarks信息
		hikariConfig.addDataSourceProperty("useInformationSchema", "true");
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(5);
		return new HikariDataSource(hikariConfig);
	}
}
