package libraryapp.repository;

import libraryapp.entity.Book;

import java.util.Collection;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */
public interface CrudRepository<K, V> {

    void save(V value);

    V get(K id);

    V get(Integer id);

    void remove(Integer id);

    Collection<V> findAll();

    void init();

    void deleteAll();

}
