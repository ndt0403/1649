package ASM2_DSA_NDT;

public interface QueueInterface<E> extends Iterable<E> {
    void enqueue(E element);

    E dequeue();

    E peek();

    int size();

    boolean isEmpty();
}
