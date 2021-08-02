package test.orm.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.After;
import org.junit.Before;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ch.qos.logback.core.db.dialect.DBUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractJPAProgrammaticBootstrapTest {

	private EntityManagerFactory emf;

	private Properties databaseProps = new Properties();
	private DataSource datasource;

	public EntityManagerFactory entityManagerFactory() {
		return emf;
	}

	@Before
	public void init() {

		loadDatabaseProperties();
		initDatasource();
		dropTables();
		
		PersistenceUnitInfo persistenceUnitInfo = persistenceUnitInfo(getClass().getSimpleName());
		
		Map<String, Object> configuration = new HashMap<>();

		emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(persistenceUnitInfo,
				configuration);
	}

	private void dropTables() {

		String databaseName = extractDatabaseName();
		String ddlTableListStatement = String.format(databaseProps.getProperty("database.table-names"), databaseName);
		String ddlDropTablePattern = databaseProps.getProperty("database.drop-table");

		log.info("Dropping tables for database {} with table query: {} and dropPattern {} ", databaseName,
				ddlTableListStatement, ddlDropTablePattern);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Statement ddlDropTableStmnt =null;
		try {
			conn = datasource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ddlTableListStatement);

			while (rs.next()) {
				String tableName = rs.getString(1);
				String ddlDropTable=String.format(ddlDropTablePattern, tableName);
				log.debug("Executing drop statement : {} ", ddlDropTable);
				ddlDropTableStmnt = conn.createStatement();
				ddlDropTableStmnt.executeUpdate(ddlDropTable);
				log.debug("Drop success for : {} ", ddlDropTable);
				
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
		} finally {
			
			DbUtils.closeQuietly(ddlDropTableStmnt);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}

	private String extractDatabaseName() {
		String dbUrl = databaseProps.getProperty("datasource.url");
		String[] dbUrlTokens = dbUrl.split("[/]");
		if (dbUrlTokens.length == 0) {
			throw new RuntimeException("Illegal Datasource Url. Found " + dbUrl);
		}
		return dbUrlTokens[dbUrlTokens.length - 1];
	}

	@After
	public void destroy() {
		emf.close();
	}

	protected PersistenceUnitInfoImpl persistenceUnitInfo(String name) {
		PersistenceUnitInfoImpl persistenceUnitInfo = new PersistenceUnitInfoImpl(name, entityClassNames(),
				hibernateProperties());

		String[] resources = resources();
		if (resources != null) {
			persistenceUnitInfo.getMappingFileNames().addAll(Arrays.asList(resources));
		}

		return persistenceUnitInfo;
	}

	protected abstract Class<?>[] entities();

	protected String[] resources() {
		return null;
	}

	protected List<String> entityClassNames() {
		return Arrays.asList(entities()).stream().map(Class::getName).collect(Collectors.toList());
	}

	protected Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", databaseProps.getProperty("hibernate.dialect")); // dataSourceProvider().hibernateDialect());
		properties.put("hibernate.hbm2ddl.auto", databaseProps.getProperty("hibernate.hbm2ddl.auto"));
		if (datasource != null) {
			properties.put("hibernate.connection.datasource", datasource);
		}
		properties.put("hibernate.generate_statistics", databaseProps.getProperty("hibernate.generate_statistics"));
		properties.put("hibernate.show_sql", databaseProps.getProperty("hibernate.showsql"));

		return properties;
	}

	protected void initDatasource() {

		HikariConfig config = new HikariConfig();
		config.setDriverClassName(databaseProps.getProperty("datasource.driver"));
		config.setJdbcUrl(databaseProps.getProperty("datasource.url"));
		config.setUsername(databaseProps.getProperty("datasource.dbuser"));
		config.setPassword(databaseProps.getProperty("datasource.dbpassword"));
		config.setMinimumIdle(Integer.parseInt(databaseProps.getProperty("datasource.minIdle")));
		config.setMaximumPoolSize(Integer.parseInt(databaseProps.getProperty("datasource.maxPoolSize")));
		config.setMaximumPoolSize(10);
		config.setAutoCommit(false);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		datasource =new HikariDataSource(config);
	}

	private void loadDatabaseProperties() {

		try {

			String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String appConfigPath = rootPath + "database.properties";
			databaseProps.load(new FileInputStream(appConfigPath));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	protected void doInJPATransaction(JPATransactionVoidFunction function) {
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			entityManager = entityManagerFactory().createEntityManager();
			function.beforeTransactionCompletion();
			txn = entityManager.getTransaction();
			txn.begin();
			function.accept(entityManager);
			if (!txn.getRollbackOnly()) {
				txn.commit();
			} else {
				try {
					txn.rollback();
				} catch (Exception e) {
					log.error("Rollback failure", e);
				}
			}
		} catch (Throwable t) {
			if (txn != null && txn.isActive()) {
				try {
					txn.rollback();
				} catch (Exception e) {
					log.error("Rollback failure", e);
				}
			}
			throw t;
		} finally {
			function.afterTransactionCompletion();
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

}
