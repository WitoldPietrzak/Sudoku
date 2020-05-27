package pl.comp.dao;

import pl.comp.dao.exceptions.DaoReadException;
import pl.comp.dao.exceptions.DaoWriteException;

public interface Dao<T> {

    T read() throws DaoReadException, ClassNotFoundException;

    void write(T obj) throws DaoWriteException, ClassNotFoundException;
}
