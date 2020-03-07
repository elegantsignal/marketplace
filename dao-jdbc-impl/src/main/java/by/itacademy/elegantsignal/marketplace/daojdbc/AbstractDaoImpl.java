package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

import by.itacademy.elegantsignal.marketplace.daoapi.IDao;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.SQLExecutionException;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.StatementAction;


public abstract class AbstractDaoImpl<ENTITY, ID> implements IDao<ENTITY, ID> {

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.user}")
	private String user;

	@Value("${jdbc.password}")
	private String password;

	@PostConstruct
	private void init() {

		if (url == null) {
			throw new IllegalArgumentException("[url] cant be null");
		}

		if (password == null) {
			throw new IllegalArgumentException("[password] cant be null");
		}

		if (user == null) {
			throw new IllegalArgumentException("[user] cant be null");
		}
	}

	@PreDestroy
	private void clean() {

	}

	@Override
	public ENTITY get(final ID id) {
		StatementAction<ENTITY> action = statement -> {
			statement.executeQuery("select * from " + getTableName() + " where id=" + id);

			final ResultSet resultSet = statement.getResultSet();
			final Set<String> columns = resolveColumnNames(resultSet);

			final boolean hasNext = resultSet.next();
			ENTITY result = null;
			if (hasNext) {
				result = parseRow(resultSet, columns);
			}

			resultSet.close();
			return result;
		};
		ENTITY entityById = executeStatement(action);
		return entityById;
	}

	@Override
	public List<ENTITY> selectAll() {
		StatementAction<List<ENTITY>> action = new StatementAction<List<ENTITY>>() {
			@Override
			public List<ENTITY> doWithStatement(final Statement statement) throws SQLException {
				statement.executeQuery("select * from " + getTableName());

				final ResultSet resultSet = statement.getResultSet();
				final Set<String> columns = resolveColumnNames(resultSet);
				final List<ENTITY> result = new ArrayList<>();
				boolean hasNext = resultSet.next();
				while (hasNext) {
					result.add(parseRow(resultSet, columns));
					hasNext = resultSet.next();
				}
				resultSet.close();
				return result;
			}
		};
		return executeStatement(action);
	}

	@Override
	public void delete(final ID id) {
		executeStatement(
				new PreparedStatementAction<Integer>(String.format("delete from %s where id=?", getTableName())) {
					@Override
					public Integer doWithPreparedStatement(final PreparedStatement prepareStatement)
							throws SQLException {
						prepareStatement.setObject(1, id);
						return prepareStatement.executeUpdate();
					}
				});
	}

	@Override
	public void deleteAll() {
		executeStatement(new PreparedStatementAction<Integer>("delete from \"" + getTableName() + "\"") {
			@Override
			public Integer doWithPreparedStatement(final PreparedStatement prepareStatement) throws SQLException {
				final int executeUpdate = prepareStatement.executeUpdate();
				return executeUpdate;
			}
		});
	}

	protected <T> T executeStatement(final StatementAction<T> action) {
		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {
			c.setAutoCommit(false);
			return action.doWithStatement(stmt);

		} catch (final SQLException e) {
			throw new SQLExecutionException(e); // wrap catchable exception with
			// runtime
		}
	}

	protected <T> T executeStatement(final PreparedStatementAction<T> action) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = action.isReturnGeneratedKeys()
						? c.prepareStatement(action.getSql(), Statement.RETURN_GENERATED_KEYS)
						: c.prepareStatement(action.getSql())) {
			c.setAutoCommit(false);
			try {
				final T doWithPreparedStatement = action.doWithPreparedStatement(pStmt);
				c.commit();
				return doWithPreparedStatement;
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	private Set<String> resolveColumnNames(final ResultSet resultSet) throws SQLException {
		final ResultSetMetaData rsMetaData = resultSet.getMetaData();
		final int numberOfColumns = rsMetaData.getColumnCount();
		final Set<String> columns = new HashSet<>();
		for (int i = 1; i <= numberOfColumns; i++) {
			columns.add(rsMetaData.getColumnName(i));
		}
		return columns;
	}

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	protected ENTITY parseRow(final ResultSet resultSet) throws SQLException {
		throw new UnsupportedOperationException(
				"this method should be overriden in particular *Impl class or use alternative "
						+ "com.itacademy.jd2.dz.cardealer.dao.jdbc.AbstractDaoImpl.parseRow(ResultSet, List<String>)");
	}

	protected ENTITY parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		// this method allows to specify in particular DAO the parser which
		// accepts list of columns. but by default it will fall back to
		// com.itacademy.jd2.dz.cardealer.dao.jdbc.AbstractDaoImpl.parseRow(ResultSet)
		return parseRow(resultSet);
	}

	protected abstract String getTableName();
}