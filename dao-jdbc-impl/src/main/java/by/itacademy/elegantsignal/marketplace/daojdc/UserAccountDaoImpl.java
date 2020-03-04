package by.itacademy.elegantsignal.marketplace.daojdc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IUserAccountDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserAccountFilter;
import by.itacademy.elegantsignal.marketplace.daojdc.entity.UserAccount;
import by.itacademy.elegantsignal.marketplace.daojdc.util.PreparedStatementAction;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void insert(final IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"insert into %s (name, email, password, created, updated) values(?,?,?,?,?)",
				getTableName()),
				true) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});

	}

	@Override
	public void update(final IUserAccount entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IUserAccount> find(final UserAccountFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final UserAccountFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setEmail(resultSet.getString("email"));
		entity.setPassword(resultSet.getString("password"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "user_account";
	}

}
