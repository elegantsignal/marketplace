package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderFilter;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Order;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.User;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;


@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {

	@Override
	public IOrder createEntity() {
		return new Order();
	}

	@Override
	public void insert(final IOrder entity) {
		executeStatement(new PreparedStatementAction<IOrder>(String.format(
				"insert into %s (user_id, created, updated) values(?,?,?)",
				getTableName()),
				true) {

			@Override
			public IOrder doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUser().getId());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IOrder entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IOrder> find(final OrderFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final OrderFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IOrder parseRow(final ResultSet resultSet) throws SQLException {
		final IOrder entity = createEntity();
		entity.setId((Integer) resultSet.getInt("id"));

		final IUser user = new User();
		user.setId(resultSet.getInt("user_id"));
		entity.setUser(user);

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "\"order\"";
	}

	@Override
	public IOrder getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
