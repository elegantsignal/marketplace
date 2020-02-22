package by.itacademy.elegantsignal.marketplace.daojdc.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class PreparedStatementAction<RETURN_TYPE> {

	private String sql;

	private boolean returnGeneratedKeys;

	public PreparedStatementAction(final String sql) {
		this(sql, false);
	}

	public PreparedStatementAction(final String sql, final boolean returnGeneratedKeys) {
		super();
		this.sql = sql;
		this.returnGeneratedKeys = returnGeneratedKeys;
	}

	public boolean isReturnGeneratedKeys() {
		return returnGeneratedKeys;
	}

	public String getSql() {
		return sql;
	}

	public abstract RETURN_TYPE doWithPreparedStatement(PreparedStatement pStmt) throws SQLException;
}