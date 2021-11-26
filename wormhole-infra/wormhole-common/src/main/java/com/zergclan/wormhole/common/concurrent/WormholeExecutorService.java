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

package com.zergclan.wormhole.common.concurrent;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executor service of Wormhole.
 */
@RequiredArgsConstructor
public final class WormholeExecutorService extends AbstractExecutorService {
    
    private final ThreadPoolExecutor threadPoolExecutor;
    
    private final WormholeRejectedHandler handler;

    /**
     * Submit task to the thread pool executor.
     *
     * @param task task
     * @param <V> the class type of result
     * @return handled result
     */
    public <V> Future<V> submit(final Callable<V> task) {
        try {
            return threadPoolExecutor.submit(task);
        } catch (RejectedExecutionException ex) {
            return handler.handle(task);
        }
    }
    
    @Override
    public void shutdown() {
        threadPoolExecutor.shutdown();
    }
    
    @Override
    public List<Runnable> shutdownNow() {
        return threadPoolExecutor.shutdownNow();
    }
    
    @Override
    public boolean isShutdown() {
        return threadPoolExecutor.isShutdown();
    }
    
    @Override
    public boolean isTerminated() {
        return threadPoolExecutor.isTerminated();
    }
    
    @Override
    public boolean awaitTermination(final long timeout, final TimeUnit unit) throws InterruptedException {
        return threadPoolExecutor.awaitTermination(timeout, unit);
    }
    
    @Override
    public void execute(final Runnable command) {
        threadPoolExecutor.execute(command);
    }
}