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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.http")
public class HttpProperties {

    /**
     * connectTimeout.
     */
    private int connectTimeout = 500;

    /**
     * readTimeout.
     */
    private int readTimeout = 1000;

    /**
     * getConnectTimeout.
     * @return int
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * setConnectTimeout.
     * @param connectTimeout value
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * getReadTimeout.
     * @return int
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * setReadTimeout.
     * @param readTimeout value
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
