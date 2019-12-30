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

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configurable
public class RestConfig {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(RestConfig.class);

    /**
     * HttpProperties.
     */
    @Autowired
    private HttpProperties httpProperties;

    /**
     * httpClient.
     * @return HttpClient
     */
    private HttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
    }

    /**
     * httpRequestFactory.
     * @return ClientHttpRequestFactory
     */
    private ClientHttpRequestFactory httpRequestFactory() {
        try {
            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory(httpClient());
            requestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
            requestFactory.setReadTimeout(httpProperties.getReadTimeout());
            return requestFactory;
        } catch (Exception e) {
            LOG.error("Failed in creating ClientHttpRequestFactory bean", e);
            return null;
        }
    }

    /**
     * restTemplate.
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate;

        ClientHttpRequestFactory requestFactory = httpRequestFactory();
        if (requestFactory != null) {
            restTemplate = new RestTemplate(requestFactory);
        } else {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }


}


