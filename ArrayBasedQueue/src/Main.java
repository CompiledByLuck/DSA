public class Main {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(3);

        System.out.println("Enqueue: 1");
        queue.enqueue(1);
        System.out.println("Enqueue: 2");
        queue.enqueue(2);
        System.out.println("Enqueue: 3");
        queue.enqueue(3);

        // Try enqueueing 4 (should throw exception)
        try {
            queue.enqueue(4);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Expected exception on enqueue: " + e.getMessage());
        }

        // Peek front element
        System.out.println("Peek: " + queue.peek()); // Should print 1

        // Dequeue all elements
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println(queue.isEmpty());

        // Try dequeue from empty queue (should throw exception)
        try {
            queue.dequeue();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Expected exception on dequeue: " + e.getMessage());
        }
    }
}