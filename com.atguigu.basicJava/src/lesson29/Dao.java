package lesson29;

public interface Dao {
	
	//常量的默认修饰为 public static final
	
	String QUERY_SQL = "SELECT ....";
	
	//接口中的方法默认修饰为: public abstract
	void save();
	
	Object get();
	
	void delete();
	
	Object[] query();
	
	
}
