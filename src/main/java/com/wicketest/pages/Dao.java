package com.wicketest.pages;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

	Optional<T> getbyID(long id);

	List<T> findAll();

	T save(T t) throws Exception;

	void delete(T t);
}
