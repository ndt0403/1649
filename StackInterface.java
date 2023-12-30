package ASM2_DSA_NDT;

public interface StackInterface<E> extends Iterable<E> {
    void push(E element);

    E pop();

    E peek();

    int size();

    boolean isEmpty();
}
