/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zergclan.wormhole.console.api.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Unified response bean for restful request.
 *
 * @param <T> class type of http result data.
 */
@Getter
@NoArgsConstructor
public final class HttpResult<T> implements Serializable {

    private int code;

    private String message;

    private T data;
    
    @Builder(toBuilder = true)
    public HttpResult(final int code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
