package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Book;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Product;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;


@Repository
public class BookDaoImpl extends AbstractDaoImpl<IBook, Integer> implements IBookDao {

	@Override
	public IBook createEntity() {
		return new Book();
	}

	@Override
	public void insert(final IBook entity) {
		executeStatement(new PreparedStatementAction<IBook>(String.format(
				"insert into %s (product_id, title, cover, published, description, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IBook doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getProduct().getId());
				pStmt.setString(2, entity.getTitle());
				pStmt.setString(3, entity.getCover().toString());
				pStmt.setObject(4, entity.getPublished(), Types.TIMESTAMP);
				pStmt.setString(5, entity.getDescription());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IBook entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IBook> find(final BookFilter filter) {
		return selectAll();
	}

	@Override
	public long getCount(final BookFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IBook parseRow(final ResultSet resultSet) throws SQLException {
		final IBook entity = createEntity();
		entity.setId(resultSet.getInt("id"));

		final IProduct product = new Product();
		product.setId(resultSet.getInt("product_id"));
		entity.setProduct(product);

		entity.setTitle(resultSet.getString("title"));
		entity.setCover(Paths.get(resultSet.getString("cover")).toString());
		entity.setPublished(resultSet.getTimestamp("published"));
		entity.setDescription(resultSet.getString("description"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "book";
	}

	@Override
	public IBook getFullInfo(final Integer id) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getFullInfo(); Timestamp: 12:06:08 AM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getFullInfo(); Timestamp: 12:06:08 AM");
		// return null;
	}

}
