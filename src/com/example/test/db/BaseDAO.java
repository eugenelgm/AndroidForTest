package com.example.test.db;

import java.util.List;
import java.util.Set;

public interface BaseDAO<T> {

	public long insertOrUpdate(T t);
	
	public void update(T t);
	
	public void insertOrUpdate(List<T> ts);
	
	public int destroy(long primaryKey);

	public int destroy(Set<Long> primaryKeys);
	
	public void destroyAll();
	
	public T get(long primaryKey);
	
	public List<T> getAll();
	
	public void truncate();
	
}
