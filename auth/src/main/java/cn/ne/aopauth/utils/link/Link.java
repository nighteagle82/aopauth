package cn.ne.aopauth.utils.link;

public interface Link<T> {
    void add(T data);
    int size();
    boolean isEmpty();
    Object[] toArray();
    T get(int index);
    T set(int index, T data);
    boolean contains(T data);
    T remove(T data);
    void clear();
}
