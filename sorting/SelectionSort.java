package sorting;

public class SelectionSort implements Sorter {

    public void sort(int[] input) {
        System.out.println("Selection Sort!!!");
        for (int start = 0; start < input.length; start++) {
            int smallest = start;
            for (int i = start; i < input.length; i++) {
                if (input[i] < input[smallest]) {
                    smallest = i;

                }
            }
            int temp = input[start];
            input[start] = input[smallest];
            input[smallest] = temp;
        }
    }
}
