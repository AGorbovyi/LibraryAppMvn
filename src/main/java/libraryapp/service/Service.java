package libraryapp.service;



public abstract class Service<T, K, V> implements IService<T, K, V> {
    private final T repository;
    private final V service;

    public Service(T repository, V service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public T getRepository() {
        return this.repository;
    }

    @Override
    public V getService() {
        return this.service;
    }
}
