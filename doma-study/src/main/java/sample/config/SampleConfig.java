package sample.config;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@SingletonConfig
public class SampleConfig implements Config {

	private static final SampleConfig CONFIG = new SampleConfig();
	private final LocalTransactionDataSource dataSource;
	private final Dialect dialect;
	private final TransactionManager transactionManager;

	private SampleConfig() {
		dialect = new PostgresDialect();
		dataSource = new LocalTransactionDataSource("jdbc:postgresql://localhost:5432/testdb", "ryu", "ryu");
		transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public Dialect getDialect() {
		return dialect;
	}

	@Override
	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public static SampleConfig singleton() {
		return CONFIG;
	}
}
