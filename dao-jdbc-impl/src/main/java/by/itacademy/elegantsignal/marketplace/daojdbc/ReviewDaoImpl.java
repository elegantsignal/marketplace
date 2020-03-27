package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IReviewDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.ReviewFilter;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.OrderItem;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Review;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;


@Repository
public class ReviewDaoImpl extends AbstractDaoImpl<IReview, Integer> implements IReviewDao {

	@Override
	public IReview createEntity() {
		return new Review();
	}

	@Override
	public void insert(final IReview entity) {
		executeStatement(new PreparedStatementAction<IReview>(String.format(
				"insert into %s (order_item_id, grade, comment, created, updated) values(?,?,?,?,?)",
				getTableName()),
				true) {

			@Override
			public IReview doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getOrderItem().getId());
				pStmt.setInt(2, entity.getGrade());
				pStmt.setString(3, entity.getComment());
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
	public void update(final IReview entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IReview> find(final ReviewFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final ReviewFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IReview parseRow(final ResultSet resultSet) throws SQLException {
		final IReview entity = createEntity();
		entity.setId((Integer) resultSet.getInt("id"));

		final IOrderItem orderItem = new OrderItem();
		orderItem.setId(resultSet.getInt("order_item_id"));
		entity.setOrderItem(orderItem);

		entity.setGrade(resultSet.getInt("grade"));
		entity.setComment(resultSet.getString("comment"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "\"review\"";
	}

	@Override
	public IReview getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getFullInfo(); Timestamp: 4:44:43 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getFullInfo(); Timestamp: 4:44:43 PM");
		// return null;
	}

}
