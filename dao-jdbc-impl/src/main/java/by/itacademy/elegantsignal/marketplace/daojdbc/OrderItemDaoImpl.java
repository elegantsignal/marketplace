package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IOrderItemDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.OrderItemFilter;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Order;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.OrderItem;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Product;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;


@Repository
public class OrderItemDaoImpl extends AbstractDaoImpl<IOrderItem, Integer> implements IOrderItemDao {

	@Override
	public IOrderItem createEntity() {
		return new OrderItem();
	}

	@Override
	public void insert(final IOrderItem entity) {
		executeStatement(new PreparedStatementAction<IOrderItem>(String.format(
				"insert into %s (order_id, product_id, amount) values(?,?,?)",
				getTableName()),
				true) {

			@Override
			public IOrderItem doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getOrder().getId());
				pStmt.setInt(2, entity.getProduct().getId());
				pStmt.setBigDecimal(3, entity.getAmount());

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
	public void update(final IOrderItem entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IOrderItem> find(final OrderItemFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final OrderItemFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IOrderItem parseRow(final ResultSet resultSet) throws SQLException {
		final IOrderItem entity = createEntity();
		entity.setId((Integer) resultSet.getInt("id"));

		final IOrder order = new Order();
		order.setId(resultSet.getInt("order_id"));
		entity.setOrder(order);

		final IProduct product = new Product();
		product.setId(resultSet.getInt("product_id"));
		entity.setProduct(product);

		entity.setAmount(resultSet.getBigDecimal("amount"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "\"order_item\"";
	}

}
