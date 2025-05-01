public class CircularQueue {
    ListNode front = null;
    ListNode rear = null;

    public CircularQueue() {
    }

    public void enqueue(int data) {
        ListNode tmp = new ListNode(data, null);
        if (front == null && rear == null) {
            front = rear = tmp;
        } else {
            rear.next = tmp;
            rear = tmp;
        }
        rear.next = front;
    }

    public int dequeue() {
        if (front == null) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty!");
        } else {
            int val = front.val;
            if (front == rear) {
                front = rear = null;
            } else {
                front = front.next;
                rear.next = front;
            }
            return val;
        }
    }

    public void print() {
        if (front == null) {
            System.out.println("Queue is empty");
        } else {
            ListNode tmp = front;
            do {
                System.out.println(tmp.val);
                tmp = tmp.next;
            } while (tmp != front);
        }
    }
}