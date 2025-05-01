public class myStack {
    private int top;
    private final int[] storage;

    public myStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Stack capacity must be positive");
        }
        storage = new int[capacity];
        top = -1;
    }

    public boolean push(int data){
        if (!isFull()){
         storage[++top] = data;
         return true;
        } else {
            throw new ArrayIndexOutOfBoundsException("Stack is full!");
        }
    }

    public int pop(){
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException("Stack is empty!");
        } else {
            return storage[top--];
        }
    }

    public int peek(){
        if (isEmpty()){
            throw new ArrayIndexOutOfBoundsException("Stack is empty!");
        } else {
            return storage[top];
        }
    }

    public int size(){
        return top + 1;
    }

    public boolean isFull(){
        return top == storage.length - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }
}