package libraryapp.service;

import java.util.HashMap;

public abstract class Service <T, K, V> implements IService<T,K,V> {
    private final HashMap<K,T> repository;
    private HashMap<K,V> services;
    public Service(HashMap<K,T> repository) {
        this.repository = repository;
        this.services=new HashMap<>();
    }
    @Override
    public HashMap<K,T> getRepositories() {
        return this.repository;
    }
    @Override
    public HashMap<K,V> getServices(){
        return this.services;
    }
    @Override
    public V getService(K key){
        return this.services.get(key);
    }
    @Override
    public T getRepository(K key){
        return this.repository.get(key);
    }
    @Override
    public void put(K key, V value){
        this.services.put(key,value);
    }
}
