public class Main {
    public static void main(String[] args) {
        Node head = new Node(4);
        head.next = new Node(3);
        head.next.next = new Node(1);
        head.next.next.next = new Node(2);

        System.out.print("Original List: ");
        printList(head);
        head = insertionSort(head);
        System.out.print("\nSorted List: ");
        printList(head);
    }

    public static Node insertionSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node sorted = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            sorted = insertIntoSortedList(sorted, current);
            current = nextNode;
        }
        return sorted;
    }

    public static Node insertIntoSortedList(Node sorted, Node newNode) {
        if (sorted == null || newNode.value <= sorted.value) {
            newNode.next = sorted;
            return newNode;
        }
        Node current = sorted;
        while (current.next != null && current.next.value < newNode.value) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        return sorted;
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}