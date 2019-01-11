package kr.misoboy.oauth.common.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author misoboy
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  -----------   ------------    ---------------------------
 *   2019-01-10       misoboy         최초 생성
 * </pre>
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Database Driver Class Name
     */
    @Value("${db.driverClassName}")
    String driverClassName;

    /**
     * Database Url
     */
    @Value("${db.url}")
    String url;

    /**
     * Database User Name
     */
    @Value("${db.username}")
    String username;

    /**
     * Database Password
     */
    @Value("${db.password}")
    String password;

    /**
     * (int) The maximum number of active connections that can be allocated from this pool at the same time.
     * The default value is 100
     */
    // 사용측 설정 가능한 값
    @Value("${db.maxActive:4}")
    int maxActive;

    /**
     * (int)The initial number of connections that are created when the pool is started. Default value is 10
     */
    // 사용측 설정 가능한 값
    @Value("${db.initialSize:4}")
    int initialSize;

    /**
     * (int) The maximum number of milliseconds that the pool will wait (when there are no available connections)
     * for a connection to be returned before throwing an exception. Default value is 30000 (30 seconds)
     */
    @Value("${db.maxWait:30000}")
    int maxWait;

    /**
     * (boolean) The indication of whether objects will be validated before being borrowed from the pool.
     * If the object fails to validate, it will be dropped from the pool, and we will attempt to borrow another.
     * NOTE - for a true value to have any effect, the validationQuery parameter must be set to a non-null string.
     * In order to have a more efficient validation, see validationInterval. Default value is false
     */
    @Value("${db.testOnBorrow:true}")
    boolean testOnBorrow;

    /**
     * (String) The SQL query that will be used to validate connections from this pool
     * before returning them to the caller. If specified, this query does not have to return any data,
     * it just can't throw a SQLException. The default value is null.
     * Example values are SELECT 1(mysql), select 1 from dual(oracle), SELECT 1(MS Sql Server)
     */
    @Value("${db.validationQuery:SELECT 1}")
    String validationQuery;

    /**
     * Database Min Idle
     */
    @Value("${db.minIdle:4}")
    Integer minIdle;

    /**
     * Database Max Idle
     */
    @Value("${db.maxIdle:4}")
    Integer maxIdle;

    /**
     * Database Validation Interval (Millisecond)
     */
    @Value("${db.validationInterval:30000}")
    Integer validationInterval;

    /**
     * Mybatis Parameter CamelCase Config
     */
    @Value("${db.mybatis.mapUnderscoreToCamelCase:true}")
    String mapUnderscoreToCamelCase;

    /**
     * Mybatis Query Cache Config
     */
    @Value("${db.mybatis.cacheEnabled:false}")
    String cacheEnabled;

    /**
     * Mybatis Alias Scan Package
     */
    @Value("${db.mybatis.typeAliasesPackage:kr.misoboy}")
    String typeAliasesPackage;

    /**
     * @return DataSource
     */
    @Bean(name = "dataSource")
    DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setValidationQueryTimeout(validationInterval);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxTotal(maxActive);
        dataSource.setMaxWaitMillis(maxWait);
        dataSource.setInitialSize(initialSize);

        return dataSource;
    }

    /**
     * sqlSessionFactory
     *
     * @return SqlSessionFactoryBean
     * @throws Exception SqlSessionFactoryBean
     */
    @Bean(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory() throws IOException {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(Boolean.valueOf(mapUnderscoreToCamelCase));
        configuration.setCacheEnabled(Boolean.valueOf(cacheEnabled));
        configuration.setJdbcTypeForNull(JdbcType.NULL);

        sessionFactory.setVfs(SpringBootVFS.class);
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        sessionFactory.setConfiguration(configuration);
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            logger.error("sqlSessionFactory 객체 생성 에러 : {}", e);
        }
        return null;
    }

    /**
     * Mybatis SqlSessionTemplate 설정
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws IOException {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
