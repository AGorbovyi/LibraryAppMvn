package libraryapp.service;

import java.util.HashMap;

public interface IService<T, K, V> {
    public HashMap<K,T> getRepositories();
    public HashMap<K,V> getServices();
    public V getService(K key);
    public void put(K name, V value);
    public T getRepository(K key);
}
