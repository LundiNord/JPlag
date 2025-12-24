public class Original {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        // Basic arithmetic operations
        int result = add(5, 3);
        System.out.println("5 + 3 = " + result);

        int difference = subtract(10, 4);
        System.out.println("10 - 4 = " + difference);

        // Array operations
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = calculateSum(numbers);
        System.out.println("Sum of array: " + sum);

        // String operations
        String message = "Java Programming";
        String reversed = reverseString(message);
        System.out.println("Original: " + message);
        System.out.println("Reversed: " + reversed);

        // Simple loop
        System.out.println("Counting from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }

        // Check even/odd
        checkEvenOdd(8);
        checkEvenOdd(7);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int calculateSum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }

    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }

    public static void checkEvenOdd(int number) {
        if (number % 2 == 0) {
            System.out.println(number + " is even");
        } else {
            System.out.println(number + " is odd");
        }
    }
}
