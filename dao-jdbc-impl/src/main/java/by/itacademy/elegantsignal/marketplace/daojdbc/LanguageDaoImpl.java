package by.itacademy.elegantsignal.marketplace.daojdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.daoapi.ILanguageDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.LanguageFilter;
import by.itacademy.elegantsignal.marketplace.daojdbc.entity.Language;
import by.itacademy.elegantsignal.marketplace.daojdbc.util.PreparedStatementAction;


@Repository
public class LanguageDaoImpl extends AbstractDaoImpl<ILanguage, Integer> implements ILanguageDao {

	@Override
	public ILanguage createEntity() {
		return new Language();
	}

	@Override
	public void insert(final ILanguage entity) {
		executeStatement(new PreparedStatementAction<ILanguage>(String.format(
				"insert into %s (code, name) values(?, ?)",
				getTableName()),
				true) {
			@Override
			public ILanguage doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getCode());
				pStmt.setString(2, entity.getName());

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
	public void update(final ILanguage entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ILanguage> find(final LanguageFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final LanguageFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ILanguage parseRow(final ResultSet resultSet) throws SQLException {
		final ILanguage entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setCode(resultSet.getString("code"));
		entity.setName(resultSet.getString("name"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "language";
	}

}
