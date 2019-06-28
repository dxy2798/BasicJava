package day_8;

/**
 * 
 * 泛型类的声明
 * 在类中凡是可以使用类型的地方都可以使用类中声明的泛型.
 * @param <T>
 */
public class Dao<T> {

	public T get(int id){
		
		T result = null;
		return result;
	}
	
	
	// 在泛型类中使用类声明的泛型.
	public void save(T entity){
		
	}
	
	/**
	 * 在类(不一定是泛型类)中使用泛型方法.
	 * 
	 * 1. 在方法的返回值前面使用<>声明泛型类型,则在方法的返回值、参数,方法体中都
	 *    可以使用该类型.
	 * @param id
	 * @return
	 */
	public <E> E getProperty(int id){
		E result = null;
		
		return result;
	}
	
	public <E> void test(E entity){
		
	}
	
}
