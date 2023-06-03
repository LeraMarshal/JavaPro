public class Pair<K, V> {
    // Напишите параметризированный класс "Pair", который принимает два различных типа данных в качестве параметров (например, "Pair")
    // Реализуйте методы для получения и установки значений каждого из элементов пары
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
