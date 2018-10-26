package com.ant.be.support;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class JsonbPostgresDialect extends PostgreSQL94Dialect{
	public JsonbPostgresDialect() {
        this.registerColumnType(Types.JAVA_OBJECT,"jsonb");
    }
}
