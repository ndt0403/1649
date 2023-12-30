package ASM2_DSA_NDT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements StackInterface<E> {
    private Node<E> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = top.element;
        top = top.next;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return top.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}
