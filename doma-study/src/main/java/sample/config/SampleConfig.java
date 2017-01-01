package sample.config;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;

public class SampleConfig implements Config {

	private static final SimpleDataSource dataSource;
	private static final Dialect dialect = new PostgresDialect();
	
	static {
		dataSource = new SimpleDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUser("postgres");
		dataSource.setPassword("postgres");
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public Dialect getDialect() {
		return dialect;
	}
	
	
}
