package cn.les;

//import org.springframework.boot.autoconfigure.jdbc.metadata.TomcatDataSourcePoolMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;

/**
 * Created by 严峰 on 2016/7/12 0012.
 */



    @Configuration

    public class DatasourceConfig {

        @Bean(name = "dataSource")
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "jdbc")
        public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }
//    @Bean(name = "dataSource_hos")
//    @ConfigurationProperties(prefix = "spring.datasource_hos")
//    public DataSource dataSource_hos() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "jdbc_hos")
//    public JdbcTemplate jdbcTemplate_hos(@Qualifier("dataSource_hos") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    }

