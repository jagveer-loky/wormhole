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

package com.zergclan.wormhole.core.data;

import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Defaulted data group.
 */
@RequiredArgsConstructor
public final class DefaultDataGroup implements DataGroup {
    
    private static final long serialVersionUID = -5547416880869227229L;
    
    private final Long planId;
    
    private final Long taskId;
    
    private final Map<String, DataNode<?>> dataNodes = new LinkedHashMap<>();
    
    @Override
    public Long getPlanId() {
        return planId;
    }
    
    @Override
    public Long getTaskId() {
        return taskId;
    }
    
    @Override
    public Optional<Map<String, DataNode<?>>> getDataNodes() {
        return dataNodes.isEmpty() ? Optional.empty() : Optional.of(dataNodes);
    }
    
    @Override
    public void init(final Map<String, DataNode<?>> dataNodes) {
        this.dataNodes.putAll(dataNodes);
    }
}