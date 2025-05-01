public class MyQueue {
    int[] storage;
    int size;
    int front;
    int rear;

    public MyQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Stack capacity must be positive");
        }
        storage = new int[capacity];
        size = 0;
        front = 0;
        rear = 0;
    }

    public boolean enqueue(int data){
        if (!isFull()){
            storage[rear++] = data;
            size++;
            return true;
        } else {
            throw new ArrayIndexOutOfBoundsException("Queue is full!");
        }
    }

    public int dequeue(){
        if (!isEmpty()){
            size--;
            return storage[front++];
        } else {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
    }

    public int peek(){
        if (!isEmpty()){
            return storage[front];
        } else {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
    }

    public boolean isFull(){
        return size == storage.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
