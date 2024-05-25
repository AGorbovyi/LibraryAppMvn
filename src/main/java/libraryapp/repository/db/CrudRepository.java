package libraryapp.repository.db;


import java.util.Collection;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */
public interface CrudRepository<K, V> {

    void save(V value);

    V get(K key);

//    V get(Integer id);

    boolean remove(K key);

    Collection<V> findAll();

    void init();

    void deleteAll();

}