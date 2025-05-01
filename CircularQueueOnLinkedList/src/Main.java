public class Main {
    public static void main(String[] args) {

        CircularQueue queue = new CircularQueue();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        try {
            queue.dequeue();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}