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

import org.assertj.core.api.Assertions;
import org.innovate.samplemicroservice.web.ApiInfoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.info.BuildProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ApiInfoControllerTest {

    @Mock
    private BuildProperties buildProperties;

    @InjectMocks
    private ApiInfoController apiInfoController;

    @Before
    public void setup() {
    }

    @Test
    public void testGetName() {
        Mockito.when(buildProperties.getName()).thenReturn("samplemicroservice");
        Assertions.assertThat(apiInfoController.getName()).isEqualTo("samplemicroservice");
    }


    @Test
    public void testGetTime() throws ParseException {
        String buildTime = "2018-06-27 06:40:34 -0500";
        Date buildTimeDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z").parse(buildTime);
        Mockito.when(buildProperties.getTime()).thenReturn(buildTimeDate.toInstant());
        Assertions.assertThat(apiInfoController.getTime()).isEqualTo(buildTime);
    }


    @Test
    public void testGetVersion() {
        Mockito.when(buildProperties.getVersion()).thenReturn("00.00.00.01");
        Assertions.assertThat(apiInfoController.getVersion()).isEqualTo("00.00.00.01");
    }

}
