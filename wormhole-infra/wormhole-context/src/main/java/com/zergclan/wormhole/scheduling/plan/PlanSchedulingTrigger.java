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

package com.zergclan.wormhole.scheduling.plan;

import com.zergclan.wormhole.definition.PlanDefinition;
import com.zergclan.wormhole.scheduling.SchedulingTrigger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Plan implemented of {@link SchedulingTrigger}.
 */
@RequiredArgsConstructor
@Getter
public final class PlanSchedulingTrigger implements SchedulingTrigger {

    private final PlanDefinition planDefinition;

    @Override
    public String getCode() {
        return planDefinition.getCode();
    }

    @Override
    public long getDelay(final TimeUnit timeUnit) {
        return 0;
    }

    @Override
    public int compareTo(final Delayed delayed) {
        return 0;
    }
}
