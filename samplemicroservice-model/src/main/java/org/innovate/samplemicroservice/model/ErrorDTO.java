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

package org.innovate.samplemicroservice.model;

/**
 * @author BChekuri
 */
public final class ErrorDTO {

    /**
     * error.
     */
    private String error;

    /**
     * getError.
     * @return String
     */
    public String getError() {
        return error;
    }

    /**
     * setError.
     * @param error string
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * ErrorDTO.
     * @param error string
     */
    public ErrorDTO(String error) {
        this.error = error;
    }
}
