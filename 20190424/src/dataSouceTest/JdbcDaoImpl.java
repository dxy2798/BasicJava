package dataSouceTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.jdbc.ReflectionUtils;


/**
 * 使用 QueryRunner 提供其具体的实现
 * @author stephen
 * @param <T> 子类需传入的泛型类型.
 */
public class JdbcDaoImpl<T> implements DAO<T> {

	private QueryRunner queryRunner= null;
	private Class<T> type;
	public JdbcDaoImpl() {
		//创建 QueryRunner 的实现类
		queryRunner = new QueryRunner();

		type = ReflectionUtils.getSuperGenericType(getClass());
	}
	
	
	
	@Override
	public void batch(Connection connection, String sql, Object[]... args) {
		try {
			queryRunner.batch(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, Object... args){
		try {
			return (E) queryRunner.query(connection, sql, args, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) {
		
		try {
			List<T> list = queryRunner.execute(connection, sql, new MyResultSetHandler(), args);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T get(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection,sql, 
				new BeanHandler<>(type), args);
		
	}

	@Override
	public void update(Connection connection, String sql, Object... args) {
		try {
			queryRunner.execute(connection, sql, args);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

}
