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

package com.zergclan.wormhole.console.application.service.impl;

import com.zergclan.wormhole.console.api.vo.PageQuery;
import com.zergclan.wormhole.console.application.domain.entity.PlanInfo;
import com.zergclan.wormhole.console.application.domain.entity.PlanTaskLinking;
import com.zergclan.wormhole.console.application.domain.entity.TaskInfo;
import com.zergclan.wormhole.console.application.service.PlanInfoService;
import com.zergclan.wormhole.console.infra.repository.BaseRepository;
import com.zergclan.wormhole.console.infra.repository.PageData;
import com.zergclan.wormhole.definition.PlanDefinition;
import com.zergclan.wormhole.definition.TaskDefinition;
import com.zergclan.wormhole.scheduling.SchedulingManager;
import com.zergclan.wormhole.scheduling.plan.PlanSchedulingManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Implemented Service of {@link PlanInfoService}.
 */
@Service(value = "planInfoService")
public class PlanInfoServiceImpl implements PlanInfoService {
    
    @Resource
    private BaseRepository<PlanInfo> planInfoRepository;
    
    @Resource
    private BaseRepository<PlanTaskLinking> planTaskLinkingRepository;
    
    @Resource
    private BaseRepository<TaskInfo> taskInfoRepository;
    
    @Override
    public void add(final PlanInfo planInfo) {
        planInfoRepository.add(planInfo);
    }
    
    @Override
    public boolean editById(final PlanInfo planInfo) {
        return planInfoRepository.edit(planInfo.getId(), planInfo);
    }
    
    @Override
    public boolean removeById(final Integer id) {
        return planInfoRepository.remove(id);
    }
    
    @Override
    public PlanInfo getById(final Integer id) {
        return planInfoRepository.get(id);
    }
    
    @Override
    public Collection<PlanInfo> listAll() {
        return planInfoRepository.listAll();
    }
    
    @Override
    public PageData<PlanInfo> listByPage(final PageQuery<PlanInfo> pageQuery) {
        return planInfoRepository.listByPage(pageQuery);
    }

    @Override
    public void triggerById(final Integer id) {
        PlanInfo planInfo = planInfoRepository.get(id);
        PlanDefinition planDefinition = initPlanDefinition(planInfo);
        SchedulingManager<PlanDefinition> schedulingManager = new PlanSchedulingManager();
        schedulingManager.execute(planDefinition);
    }

    private PlanDefinition initPlanDefinition(final PlanInfo planInfo) {
        String code = planInfo.getCode();
        Integer executionModeCode = planInfo.getExecutionMode();
        String executionCorn = planInfo.getExecutionCorn();
        Integer operator = planInfo.getOperator();
        PlanDefinition result = new PlanDefinition(code, executionModeCode, executionCorn, operator);
        Collection<TaskInfo> tasks = listTask(planInfo);
        for (TaskInfo each : tasks) {
            result.registerTask(initTaskDefinition(each));
        }
        return result;
    }

    private Collection<TaskInfo> listTask(final PlanInfo planInfo) {
        PlanTaskLinking query = new PlanTaskLinking();
        query.setPlanId(planInfo.getId());
        Collection<PlanTaskLinking> planTaskLinking = planTaskLinkingRepository.list(query);
        if (planTaskLinking.isEmpty()) {
            return new LinkedList<>();
        }
        Collection<Integer> taskIds = initTaskIds(planTaskLinking);
        return taskInfoRepository.list(taskIds);
    }

    private Collection<Integer> initTaskIds(final Collection<PlanTaskLinking> planTaskLinking) {
        Collection<Integer> result = new LinkedList<>();
        for (PlanTaskLinking each : planTaskLinking) {
            result.add(each.getTaskId());
        }
        return result;
    }

    private TaskDefinition initTaskDefinition(final TaskInfo taskInfo) {
        String code = taskInfo.getCode();
        return new TaskDefinition(code);
    }
}
