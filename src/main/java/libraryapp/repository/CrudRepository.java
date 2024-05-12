package libraryapp.repository;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Boiko Yaroslav
 * @version 22-Apr-24
 */
public interface CrudRepository<K, V> {

    void put(V value);

    V get (K key);

    void remove (V value);

    Iterable<V> values();

    void init();

}
