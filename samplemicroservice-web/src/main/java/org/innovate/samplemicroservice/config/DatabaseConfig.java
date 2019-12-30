/*
 * Copyright (c) 2019.  BChekuri
 *
 * Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *              http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.innovate.samplemicroservice.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    /**
     * LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

    /**
     * Environment.
     */
    @Autowired
    private Environment environment;

    /**
     * getDataSource.
     * @param dataSourceProperties to create datasource
     * @return DataSource
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource getDataSource(DataSourceProperties dataSourceProperties) {
        LOG.info("Spring Active Profile - " + environment.getActiveProfiles()
                + ", Db url - " + dataSourceProperties.getUrl());
        return DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .type(HikariDataSource.class)
                .url(dataSourceProperties.getUrl())
                .password(dataSourceProperties.getPassword())
                .username(dataSourceProperties.getUsername())
                .build();
    }


}
