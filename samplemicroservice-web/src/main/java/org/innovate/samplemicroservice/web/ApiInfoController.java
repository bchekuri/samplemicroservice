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

package org.innovate.samplemicroservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BChekuri
 */
@RestController
@RequestMapping("/build")
public class ApiInfoController {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ApiInfoController.class);

    /**
     * BuildProperties.
     */
    @Autowired(required = false)
    private BuildProperties buildProperties;

    /**
     * getVersion.
     * @return String
     */
    @GetMapping(value = "/version")
    public String getVersion() {
        log.debug("get build version");
        return buildProperties.getVersion();
    }


    /**
     * getName.
     * @return String
     */
    @GetMapping(value = "/name")
    public String getName() {
        return buildProperties.getName();
    }

    /**
     * getTime.
     * @return String
     * @throws ParseException when error
     */
    @GetMapping(value = "/time")
    public String getTime() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");
        return simpleDateFormat.format(Date.from(buildProperties.getTime()));
    }

}
