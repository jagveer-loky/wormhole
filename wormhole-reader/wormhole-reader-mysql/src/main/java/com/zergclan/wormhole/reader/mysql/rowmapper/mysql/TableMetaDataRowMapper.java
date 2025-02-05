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

package com.zergclan.wormhole.reader.mysql.rowmapper.mysql;

import com.zergclan.wormhole.core.metadata.TableMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSet to entity conversion.
 */
@RequiredArgsConstructor
public final class TableMetaDataRowMapper implements RowMapper<TableMetaData> {

    private final String databaseIdentifier;

    @Override
    public TableMetaData mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        TableMetaData tableMetaData = new TableMetaData(databaseIdentifier, rs.getString("TABLE_SCHEMA"),
                rs.getString("TABLE_NAME"), rs.getString("TABLE_COMMENT"));
        return tableMetaData;
    }
}
