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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.innovate.samplemicroservice.error.DefaultExceptionHandler;
import org.innovate.samplemicroservice.web.SampleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SampleController.class})
@WebAppConfiguration
public class SampleControllerTest {

    private MockMvc mockMvc;

    @Before
    public void testGetSample() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new SampleController())
                .setControllerAdvice(new DefaultExceptionHandler())
                .build();
    }

    @Test
    public void testGetSampleShouldReturnInternalError() throws Exception {
        mockMvc.perform(get("/v1/sample"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{'error':'Internal Server Error'}"));
    }


    @Test
    public void testGetSampleShouldReturnOk() throws Exception {
        mockMvc.perform(get("/v1/sample?message=good"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'good'}"));
    }

}
