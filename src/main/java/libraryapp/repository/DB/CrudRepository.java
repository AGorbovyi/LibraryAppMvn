package libraryapp.repository.DB;


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

    boolean remove(Integer id);

    Collection<V> findAll();

    void init();

    void deleteAll();

}