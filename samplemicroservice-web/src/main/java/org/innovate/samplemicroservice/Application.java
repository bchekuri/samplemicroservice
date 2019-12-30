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

package org.innovate.samplemicroservice;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.innovate.samplemicroservice.constant.ApiInfoConstant.API_NAME;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.API_DESC;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.API_VERSION;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.TERMS_OF_SERVICE_URL;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.CONTACT_NAME;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.CONTACT_URL;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.CONTACT_EMAIL;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.LICENSE;
import static org.innovate.samplemicroservice.constant.ApiInfoConstant.LICENSE_URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"org.innovate.samplemicroservice"})
@EnableAutoConfiguration
@EnableConfigurationProperties
public class Application extends AsyncConfigurerSupport {

	/**
	 * LOGGER.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	/**
	 *
	 * @param args arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		beanInfo();
	}

	/**
	 * BeanInfo.
	 */
	public static void beanInfo() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		LOG.debug("Boot Class Path : {}", runtimeMXBean.getBootClassPath());
		LOG.debug("Library Path : {}", runtimeMXBean.getLibraryPath());
		LOG.debug("Class Path : {}", runtimeMXBean.getClassPath());
	}

	/**
	 * apiInfo.
	 * @return Docket
	 */
	@Bean
	public Docket apiInfo() {
		ApiInfo apiInfo = new ApiInfo(API_NAME,
				API_DESC,
				API_VERSION,
				TERMS_OF_SERVICE_URL,
				new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL),
				LICENSE,
				LICENSE_URL,
				Collections.emptyList());
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.genericModelSubstitutes(ResponseEntity.class)
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.directModelSubstitute(org.joda.time.LocalDate.class, String.class)
				.directModelSubstitute(org.joda.time.LocalDate.class, Date.class)
				.directModelSubstitute(org.joda.time.DateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDate.class, String.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.select()
				.paths(PathSelectors.any())
				.build();

		docket.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						Arrays.asList(new ResponseMessageBuilder()
										.code(HttpStatus.INTERNAL_SERVER_ERROR
												.value())
										.message(HttpStatus.INTERNAL_SERVER_ERROR
												.getReasonPhrase())
										.build(),
								new ResponseMessageBuilder()
										.code(HttpStatus.FORBIDDEN.value())
										.message(HttpStatus.FORBIDDEN
												.getReasonPhrase())
										.build(),
								new ResponseMessageBuilder()
										.code(HttpStatus.NOT_FOUND
												.value())
										.message(HttpStatus.NOT_FOUND
												.getReasonPhrase())
										.build()));
		return docket;
	}
}
