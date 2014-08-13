package structure.queue;

import common.exception.EmptyQueueException;

/**
 * Created by boyce on 2014/7/16.
 */
public class ILinkedQueue<T> implements IQueue<T> {

    private int size;
    private Node front;
    private Node rear;

    public ILinkedQueue() {
        this.front = new Node();
        this.rear = new Node();
        this.front.next = rear;
        this.rear.prior = front;
    }

    @Override
    public void offer(T t) {
        Node n = new Node(t);

        this.front.next.prior = n;
        n.prior = this.front;

        n.next = this.front.next;
        this.front.next = n;

        this.size ++;
    }

    @Override
    public T poll() {
        T t = this.peek();

        this.rear.prior.prior.next = this.rear;
        this.rear.prior = this.rear.prior.prior;

        this.size --;

        return t;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyQueueException();

        return (T)this.rear.prior.data;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.front.next = rear;
        this.rear.prior = front;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node p = this.front;
        while (p.next != this.rear) {
            p = p.next;
            builder.append(p.data).append(", ");
        }

        if (builder.length() > 2)
            builder.delete(builder.length()-2, builder.length());

        return builder.append("]").toString();
    }

    private static class Node<T> {
        private Node next;
        private Node prior;
        private T data;

        private Node(Node next, T data) {
            this.data = data;
            this.next = next;
        }

        private Node(T data) {
            this(null, data);
        }

        private Node() {
            this(null, null);
        }
    }

    public static void main(String[] args) {
        IQueue queue = new ILinkedQueue();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");


        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);

        queue.offer("4");
        queue.offer("5");
        queue.offer("6");

        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
