public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 88};
        //System.out.println(binarySearchRegular(arr, 6));
        System.out.println(binarySearchRecursive(arr, 88, 0, arr.length - 1));
    }

    public static int binarySearchRegular(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] array, int target, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                return binarySearchRecursive(array, target, low, mid - 1);
            } else
                return binarySearchRecursive(array, target, mid + 1, high);
        } else return -1;
    }
}