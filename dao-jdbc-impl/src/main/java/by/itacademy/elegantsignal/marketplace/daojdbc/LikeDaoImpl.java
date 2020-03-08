package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.ILikeDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.LikeFilter;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Like;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Product;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.UserAccount;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;


@Repository
public class LikeDaoImpl extends AbstractDaoImpl<ILike, Integer> implements ILikeDao {

	@Override
	public ILike createEntity() {
		return new Like();
	}

	@Override
	public void insert(final ILike entity) {
		executeStatement(new PreparedStatementAction<ILike>(String.format(
				"insert into %s (user_account_id, product_id, created) values(?,?,?)",
				getTableName()),
				true) {
			@Override
			public ILike doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setInt(2, entity.getProduct().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);

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
	public void update(final ILike entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ILike> find(final LikeFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final LikeFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ILike parseRow(final ResultSet resultSet) throws SQLException {
		final ILike entity = createEntity();
		
		
		entity.setId((Integer) resultSet.getInt("id"));

		final IUserAccount userAccount = new UserAccount();
		userAccount.setId(resultSet.getInt("user_account_id"));
		entity.setUserAccount(userAccount);

		final IProduct product = new Product();
		product.setId(resultSet.getInt("product_id"));
		entity.setProduct(product);
		
		entity.setCreated(resultSet.getTimestamp("created"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "_like";
	}

}
