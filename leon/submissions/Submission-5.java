public class MathHelper {
    private static final String GREETING_TEXT = "Hello, world!";

    public static void main(String[] args) {

            System.out.println(GREETING_TEXT);

            int firstNum = 5, secondNum = 3;
            int additionResult = performAddition(firstNum, secondNum);
            System.out.println(firstNum + " + " + secondNum + " = " + additionResult);

            int minuend = 10, subtrahend = 4;
            int subtractionResult = performSubtraction(minuend, subtrahend);
            System.out.println(minuend + " - " + subtrahend + " = " + subtractionResult);

            int[] dataSet = new int[]{1, 2, 3, 4, 5};
            int totalSum = computeArraySum(dataSet);
            System.out.println("Sum of array: " + totalSum);

            String originalText = "Java Programming";
            String flippedText = flipString(originalText);
            System.out.println("Original: " + originalText);
            System.out.println("Reversed: " + flippedText);

            System.out.println("Counting from 1 to 5:");
            int counter = 1;
            while (counter <= 5) {
                System.out.println("Count: " + counter);
                counter++;
        }

    }

    private static void analyzeNumbers() {
        int[] testNumbers = {8, 7};
        for (int value : testNumbers) {
            determineEvenOrOdd(value);
        }
    }

    // Mathematical operations with different parameter names
    public static int performAddition(int operandA, int operandB) {
        int sum = operandA + operandB;
        return sum;
    }

    public static int performSubtraction(int operandA, int operandB) {
        int difference = operandA - operandB;
        return difference;
    }

    // Array processing with different approach
    public static int computeArraySum(int[] inputArray) {
        int runningTotal = 0;
        int index = 0;
        while (index < inputArray.length) {
            runningTotal = runningTotal + inputArray[index];
            index++;
        }
        return runningTotal;
    }

    // String manipulation with different implementation
    public static String flipString(String inputText) {
        char[] characters = inputText.toCharArray();
        String result = "";
        for (int pos = characters.length - 1; pos >= 0; pos--) {
            result = result + characters[pos];
        }
        return result;
    }

    // Number analysis with different structure
    public static void determineEvenOrOdd(int value) {
        String classification = (value % 2 == 0) ? "even" : "odd";
        System.out.println(value + " is " + classification);
    }
}
