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

package org.apache.shardingsphere.core.parse.filler.common.dal;

import org.apache.shardingsphere.core.parse.filler.api.SQLSegmentFiller;
import org.apache.shardingsphere.core.parse.sql.segment.common.SchemaSegment;
import org.apache.shardingsphere.core.parse.sql.statement.SQLStatement;
import org.apache.shardingsphere.core.parse.sql.statement.dal.dialect.mysql.statement.ShowColumnsStatement;
import org.apache.shardingsphere.core.parse.sql.statement.dal.dialect.mysql.statement.ShowIndexStatement;
import org.apache.shardingsphere.core.parse.sql.statement.dal.dialect.mysql.statement.UseStatement;
import org.apache.shardingsphere.core.parse.sql.token.impl.SchemaToken;

/**
 * Schema filler.
 *
 * @author zhangliang
 */
public final class SchemaFiller implements SQLSegmentFiller<SchemaSegment> {
    
    @Override
    public void fill(final SchemaSegment sqlSegment, final SQLStatement sqlStatement) {
        if (sqlStatement instanceof UseStatement) {
            ((UseStatement) sqlStatement).setSchema(sqlSegment.getName());
        } else if (sqlStatement instanceof ShowColumnsStatement || sqlStatement instanceof ShowIndexStatement) {
            sqlStatement.addSQLToken(new SchemaToken(sqlSegment.getStartIndex(), sqlSegment.getStopIndex(), sqlSegment.getName(), sqlStatement.getTables().getSingleTableName()));
        }
    }
}
