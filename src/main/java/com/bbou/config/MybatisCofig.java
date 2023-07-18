package com.bbou.config;


import javax.sql.DataSource;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class MybatisCofig {
	
	
	//히카리 데이터소스  mybatis-jpa  // jpa 보다 2가지추가
	@Bean
	DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	
	//http://mybatis.org/spring/getting-started.html참고
	//In MyBatis-Spring, an SqlSessionFactoryBean is used to create an SqlSessionFactory.
	
	@Bean // 프로퍼티즈에서 가져오는값 jpa 보다 2가지추가
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	private final ApplicationContext ap;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		System.out.println(">>>>" + dataSource());
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfiguration(mybatisConfiguration());
		
		String locationPattern="classpath:/mapper/**/*-mapper.xml";
		Resource[] mapperLocations=ap.getResources(locationPattern);
		//new classloaderfileresourc
		factoryBean.setMapperLocations(mapperLocations);  //xml파일위치 설정
		
		return factoryBean.getObject();
		
	}
	@Bean //camel 세팅
	@ConfigurationProperties(prefix = "mybatis.configuration")
	org.apache.ibatis.session.Configuration mybatisConfiguration() {
		org.apache.ibatis.session.Configuration config=new org.apache.ibatis.session.Configuration();
		config.setLogImpl(StdOutImpl.class);
		return config;
	}

	//2.Session템플릿    http://mybatis.org/spring/sqlsession.html karh 참고
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	

	
}
