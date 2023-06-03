import java.util.NoSuchElementException;

public class Stack<T> {
    // Создайте параметризированный класс "Stack", представляющий стек элементов
    // Реализуйте методы "push", "pop" и "isEmpty"
    private class Node<T> {
        T data;
        Node<T> next;
    }

    private Node<T> head;

    public void push(T data) {
        Node<T> node = new Node<>();

        node.data = data;
        if (head != null) {
            node.next = head;
        }
        head = node;
    }

    public T pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        T data = head.data;
        head = head.next;

        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

