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

import com.zergclan.wormhole.console.api.security.UserSessionManager;
import com.zergclan.wormhole.console.api.vo.HttpResult;
import com.zergclan.wormhole.console.api.vo.LoginVO;
import com.zergclan.wormhole.console.api.vo.ResultCode;

import com.zergclan.wormhole.console.application.domain.entity.UserInfo;
import com.zergclan.wormhole.console.application.service.LoginService;
import com.zergclan.wormhole.console.infra.anticorruption.AntiCorruptionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Controller of {@link UserInfo} Login.
 */
@RestController
@RequestMapping("/security")
public class LoginController extends AbstractRestController {
    
    @Resource
    private LoginService loginService;
    
    /**
     * Login.
     *
     * @param loginVO {@link LoginVO}
     * @return {@link HttpResult}
     */
    @PostMapping(value = "/login")
    public HttpResult<String> login(@RequestBody final LoginVO loginVO) {
        Optional<UserInfo> userInfo = AntiCorruptionService.userLoginVOToPO(loginVO);
        if (userInfo.isPresent()) {
            Optional<String> token = loginService.login(userInfo.get());
            if (token.isPresent()) {
                return success(ResultCode.SUCCESS, token.get());
            }
        }
        return failed(ResultCode.UNAUTHORIZED, "");
    }
    
    /**
     * Logout.
     *
     * @return {@link HttpResult}
     */
    @PostMapping(value = "/logout")
    public HttpResult<Void> logout() {
        UserSessionManager.clearUserSession(getToken());
        return success();
    }
}
