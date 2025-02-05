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

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Query VO of page.
 *
 * @param <T> class type of query object
 */
public final class PageQuery<T> implements Serializable {
    
    private static final long serialVersionUID = 7535045351524894851L;
    
    @Min(value = 1, message = "page number must greater than 0 ")
    @Getter
    @Setter
    private Integer page;
    
    @Range(min = 2, max = 10, message = "page size must greater than 2 Less than 10 ")
    @Getter
    @Setter
    private Integer size;
    
    @Getter
    @Setter
    private T query;
    
    /**
     * Get start number for page.
     *
     * @return start number
     */
    public Integer getStart() {
        return (page - 1) * size;
    }
}
