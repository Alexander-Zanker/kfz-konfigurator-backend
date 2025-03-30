package de.computacenter.kfzkonfigurator;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        // Ensure the driver class is set if it's missing
        if (dataSourceProperties.getDriverClassName() == null) {
            dataSourceProperties.setDriverClassName("org.postgresql.Driver");
        }
        // Build and return a HikariDataSource
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
}
