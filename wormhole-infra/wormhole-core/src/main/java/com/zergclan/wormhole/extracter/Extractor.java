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

package com.zergclan.wormhole.extracter;

import com.zergclan.wormhole.core.metadata.ColumnMetaData;
import com.zergclan.wormhole.core.metadata.IndexMetaData;
import com.zergclan.wormhole.core.metadata.SchemaMetaData;
import com.zergclan.wormhole.core.metadata.TableMetaData;

import java.util.Collection;
import java.util.Map;

/**
 * The root interface from which all extractor shall be derived in Wormhole.
 */
public interface Extractor {

    /**
     * Extract {@link TableMetaData}.
     *
     * @param schemaMetaData {@link SchemaMetaData}
     * @return {@link TableMetaData}
     */
    Collection<TableMetaData> extractTables(SchemaMetaData schemaMetaData);

    /**
     * Extract {@link ColumnMetaData} of {@link TableMetaData}.
     *
     * @param tableMetaData {@link TableMetaData}
     * @return {@link ColumnMetaData}
     */
    Collection<ColumnMetaData> extractColumns(TableMetaData tableMetaData);

    /**
     * Extract {@link IndexMetaData} of {@link TableMetaData}.
     *
     * @param table {@link TableMetaData}
     * @return {@link IndexMetaData}
     */
    Collection<IndexMetaData> extractIndexes(TableMetaData table);

    /**
     * Extract datum.
     *
     * @param columns {@link ColumnMetaData}
     * @return datum
     */
    Collection<Map<String, Object>> extractDatum(Map<String, ColumnMetaData> columns);
}
