package libraryapp.service;

public abstract class Service <T> {
    private T repository;
    public Service(T repository) {
        this.repository = repository;
    }

    public T getRepository() {
        return this.repository;
    }

}
