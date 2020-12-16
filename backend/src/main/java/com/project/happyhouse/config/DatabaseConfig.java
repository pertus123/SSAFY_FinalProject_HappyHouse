package com.project.happyhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.project.happyhouse.model.mapper"
)
public class DatabaseConfig {}