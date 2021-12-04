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

package com.zergclan.wormhole.console.api.contorller;

import com.zergclan.wormhole.console.api.vo.HttpResult;
import com.zergclan.wormhole.console.api.vo.LoginVO;
import com.zergclan.wormhole.console.api.vo.ResultCode;
import com.zergclan.wormhole.console.application.domain.entity.UserInfo;
import com.zergclan.wormhole.console.application.domain.value.RootUser;
import com.zergclan.wormhole.console.infra.anticorruption.AntiCorruptionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of Login.
 */
@RestController
public final class LoginController extends AbstractRestController {

    /**
     * Login.
     *
     * @param loginVO {@link LoginVO}
     * @return {@link HttpResult}
     */
    @PostMapping(value = "/login")
    public HttpResult<String> login(@RequestBody final LoginVO loginVO) {
        UserInfo userInfo = AntiCorruptionService.userLoginVOToDTO(loginVO);
        return RootUser.ROOT.isRoot(userInfo.getUsername(), userInfo.getPassword()) ? success(ResultCode.SUCCESS, "wormhole-root-token") : failed(ResultCode.UNAUTHORIZED, "");
    }
}
