package dao;

import java.util.TreeSet;

public interface DAOInterface<T> {
	public void insert(T t);

	public void update(T t);

	public void delete(T t);

	public TreeSet<T> selectAll();

	public T selectById(T t);
	
	public TreeSet<T> selectByCondition(String condition);
}
