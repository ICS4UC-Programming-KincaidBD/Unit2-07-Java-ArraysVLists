import java.util.*;

/*
@author KincaidBD
@since 2026-11-11
@version 1.0
*/

// This class reads numbers from text files and then finds the mean,
// median, and mode of the valid numbers in each file.
// The goal is to compare how arrays and lists can be used together.
public class ArraysVLists {

    // This is the main method. It runs when the program starts.
    public static void main(String[] args) {
        // These are the file names we want to read.
        String[] files = { "Unit2-06-Set1.txt", "Unit2-06-Set2.txt", "Unit2-06-Set3.txt" };

        // Go through each file one at a time.
        for (String fileName : files) {
            try {
                // A list is used because it can grow as we read the file.
                // We are storing only valid integers from the file.
                List<Integer> dynamicList = new ArrayList<>();

                // Open the file so we can read it line by line.
                BufferedReader openFile = new BufferedReader(new FileReader(fileName));
                String line;

                // Read every line in the file.
                while ((line = openFile.readLine()) != null) {
                    try {
                        // Turn the text into an integer and add it to the list.
                        dynamicList.add(Integer.parseInt(line.trim()));
                    } catch (NumberFormatException ignored) {
                        // If a line is blank or has text instead of a number,
                        // skip it and keep going. This is like ignoring bad data.
                    }
                }

                // Close the file after we're done reading it.
                openFile.close();

                // If no valid numbers were found, show an error message.
                if (dynamicList.isEmpty()) {
                    System.out.println("Error: File is empty or has no valid integers.");
                    continue;
                }

                // The list size tells us how many valid numbers we have.
                int numberOfItems = dynamicList.size();

                // Create an array with the same number of items as the list.
                // This is useful because arrays are easier to sort and calculate stats with.
                int[] fixedArray = new int[numberOfItems];

                // Copy the list items into the array.
                for (int i = 0; i < numberOfItems; i++) {
                    fixedArray[i] = dynamicList.get(i);
                }

                // Print the numbers in the array.
                System.out.println("Numbers in file: " + Arrays.toString(fixedArray));

                // Sort the numbers from smallest to largest.
                Arrays.sort(fixedArray);

                // Find the mean, median, and mode.
                double meanResult = CalcMean(fixedArray);
                double medianResult = CalcMedian(fixedArray);
                List<Integer> modeResult = CalcMode(fixedArray);

                // Print the results for this file.
                System.out.println("Mean: " + meanResult);
                System.out.println("Median: " + medianResult);
                System.out.println("Mode: " + modeResult);
                System.out.println();
            } catch (IOException error) {
                // If the file cannot be opened or read, tell the user.
                System.out.println("Error reading file: " + fileName);
            }
        }
    }

    // This method finds the average of all numbers in the array.
    // The mean is the sum divided by how many numbers there are.
    public static double CalcMean(int[] arr) {
        int sum = 0;

        // Add every number in the array together.
        for (int number : arr) {
            sum += number;
        }

        // Divide the total by the amount of items to get the average.
        return (double) sum / arr.length;
    }

    // This method finds the middle number in a sorted list.
    // If there are two middle numbers, it averages them.
    public static double CalcMedian(int[] arr) {
        int middle = arr.length / 2;

        // If the array has an even number of items, take the two middle values.
        if (arr.length % 2 == 0) {
            return (arr[middle - 1] + arr[middle]) / 2.0;
        }

        // If the array has an odd number of items, the middle one is the median.
        return arr[middle];
    }

    // This method finds the mode, which is the number that appears most often.
    // If there is a tie, it returns all the numbers that tie for the most repeats.
    public static List<Integer> CalcMode(int[] arr) {
        // This list will hold all the mode values.
        List<Integer> modesList = new ArrayList<>();
        int maxFrequency = 0;

        // Check each number in the array.
        for (int i = 0; i < arr.length; i++) {
            int frequency = 1;

            // Compare it to every other number in the array.
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j]) {
                    frequency++;
                }
            }

            // If this number appears more than any other so far,
            // it becomes the new mode and the list resets.
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                modesList.clear();
                modesList.add(arr[i]);
            }
            // If this number ties the current top frequency, add it too.
            else if (frequency == maxFrequency && !modesList.contains(arr[i])) {
                modesList.add(arr[i]);
            }
        }

        // Return the list of mode numbers.
        return modesList;
    }
}