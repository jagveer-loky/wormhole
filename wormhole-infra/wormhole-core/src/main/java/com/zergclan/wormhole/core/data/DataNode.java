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

/**
 * The root interface from which all converted data node objects shall be derived in Wormhole.
 *
 * @param <V> class type of data node value
 */
public interface DataNode<V> {
    
    /**
     * Get data node value.
     *
     * @return data node value
     */
    V getValue();
    
    /**
     * Get data node name.
     *
     * @return data node name
     */
    String getName();
    
    /**
     * Refresh {@link DataNode}.
     *
     * @param value data node value
     * @return {@link DataNode}
     */
    DataNode<V> refresh(V value);
}
