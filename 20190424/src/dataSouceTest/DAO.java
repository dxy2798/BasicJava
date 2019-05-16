package dataSouceTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 访问数据的 DAO 接口
 * 里面定义好访问数据表的各种方法
 * @param T: DAO 处理的实体类的类型.
 * @author stephen
 *
 */
public interface DAO<T> {
	
	@SuppressWarnings("rawtypes")
	class MyResultSetHandler implements ResultSetHandler{

		@Override
		public Object handle(ResultSet resultSet) 
				throws SQLException {
			
			List<Customer> customers = new ArrayList<>();
			
			while(resultSet.next()){
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				Date birth = resultSet.getDate(4);
				
				Customer customer = 
						new Customer(id, name, email, birth);
				customers.add(customer);
			}
			return customers;
		}
	}
	
	
	
	
	
	
	
	/**
	 * 批量处理的方法
	 * @param connection
	 * @param sql
	 * @param args: 填充占位符的 Objecr[] 类型的可变参数
	 */
	void batch(Connection connection,String sql,
			Object [] ...args);
	
	
	/**
	 * 返回具体的一个值,例如总人数,平均工资,某个人的 email 等
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	<E> E getForValue(Connection connection,String sql,
			Object ...args) throws SQLException;
	
	
	/**
	 * 返回 T 的一个集合
	 */
	List<T> getForList(Connection connection,String sql,
			Object ...args);
	
	
	/**
	 * 返回一个 T 的对象
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	T get(Connection connection,String sql,
			Object ...args) throws SQLException;
	
	
	/**
	 * INSERT,UPDATE,DELETE
	 * @param connection: 数据库连接
	 * @param sql: SQL语句
	 * @param args: 填充占位符的可变参数
	 * @return 
	 */
	void update(Connection connection,String sql,
			Object ... args);
}
