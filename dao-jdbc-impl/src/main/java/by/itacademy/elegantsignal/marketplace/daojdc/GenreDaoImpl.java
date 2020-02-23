package by.itacademy.elegantsignal.marketplace.daojdc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.IGenreDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.daojdc.entity.Genre;
import by.itacademy.elegantsignal.marketplace.daojdc.util.PreparedStatementAction;

public class GenreDaoImpl extends AbstractDaoImpl<IGenre, Integer> implements IGenreDao {

	@Override
	public IGenre createEntity() {
		return new Genre();
	}

	@Override
	public void insert(final IGenre entity) {
		executeStatement(new PreparedStatementAction<IGenre>(
				String.format("insert into %s (name) values(?)", getTableName()), true) {
			@Override
			public IGenre doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());

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
	public void update(final IGenre entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IGenre> find(final GenreFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final GenreFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IGenre parseRow(final ResultSet resultSet) throws SQLException {
		final IGenre entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setName(resultSet.getString("name"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "genre";
	}

}
