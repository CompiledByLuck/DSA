public class Main {
    public static void main(String[] args) {
        myStack stack = new myStack(3);

        for (int i = 0; i < 3; i++) {
            System.out.println("Pushing: " + i);
            stack.push(i);
        }

        try {
            System.out.println("Attempting to push into full stack...");
            stack.push(100);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        System.out.println("Size: " + stack.size());
        System.out.println("Peek: " + stack.peek());

        for (int i = 0; i < 3; i++) {
            int popped = stack.pop();
            System.out.println("Popped: " + popped);
        }

        try {
            System.out.println("Attempting to pop from empty stack...");
            stack.pop();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}