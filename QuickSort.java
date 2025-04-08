import java.util.ArrayList;
import java.util.Random;




public class QuickSort{
    public static void main(String[] args){
        ArrayList<Integer> day1 = new ArrayList<Integer>();
        ArrayList<Integer> day2 = new ArrayList<Integer>();
        ArrayList<Integer> day3 = new ArrayList<Integer>();
        ArrayList<Integer> day4 = new ArrayList<Integer>();
        ArrayList<Integer> day5 = new ArrayList<Integer>();
        ArrayList<Integer> day6 = new ArrayList<Integer>();
        ArrayList<Integer> day7 = new ArrayList<Integer>();

        populateArrayList(day1,1000);
        populateArrayList(day2, 5000);
        populateArrayList(day3, 10000);
        populateArrayList(day4, 50000);
        populateArrayList(day5, 75000);
        populateArrayList(day6, 100000);
        populateArrayList(day7,500000);


        
        calcAvgExecutionTime(day7,100);
    
    }

    public static long calcTimeDiff(ArrayList<Integer> arr){
        ArrayList<Integer> arr2 = new ArrayList<>(arr);
        long startTime = System.currentTimeMillis();
        quickSort(arr2, 0, arr2.size()-1);
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    public static void calcAvgExecutionTime(ArrayList<Integer> arr, int iterations){
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        ArrayList<Integer> reverseSorted = new ArrayList<Integer>();
        long totalTimeRandom = 0;
        long totalTimeSorted = 0;
        long totalTimeReversed = 0;
        

        for (Integer num: arr){
            sorted.add(num);
        }

        quickSort(sorted,0,sorted.size()-1);

        for (int j = sorted.size()-1; j > -1; j--){
            reverseSorted.add(sorted.get(j));
        }

        
        for (int i = 0; i < iterations; i++){
            totalTimeRandom += calcTimeDiff(arr);            
            totalTimeSorted += calcTimeDiff(sorted);
            totalTimeReversed += calcTimeDiff(reverseSorted);

        }

        printTimes(sorted, totalTimeRandom, totalTimeSorted, totalTimeReversed, iterations);
        
    }


    public static void printTimes(ArrayList<Integer> sorted, long randTime, long sortedTime, long presortTime, int iterations){
        double avgRand = (double)randTime/iterations;
        double avgSorted = (double)sortedTime/iterations;
        double avgPresorted = (double)presortTime/iterations;
        double avgTime = (avgRand + avgSorted + avgPresorted)/3;


        System.out.printf("Average time across %d iterations on a random list: %.2f ms",iterations,avgRand);
        System.out.println();
        System.out.printf("Average time across %d iterations on a pre sorted list: %.2f ms",iterations,avgSorted);
        System.out.println();
        System.out.printf("Average time across %d iterations on a reverse sorted list: %.2f ms",iterations,avgPresorted);
        System.out.println();
        System.out.printf("The average time is %.2f ms", avgTime);
        System.out.println();
        System.out.printf("The first 5 items are: ");
        for (int i = 0; i < 5; i++){
            System.out.printf("%d, ",sorted.get(i));
        }
        System.out.println();
        System.out.printf("The last 5 items are: ");
        for (int i = sorted.size()-6; i < sorted.size()-1; i++){
            System.out.printf("%d, ",sorted.get(i));
        }
    }

    


    public static void populateArrayList(ArrayList<Integer> arr, int size){

        Random rand = new Random();
        for (int i = 1; i< size + 1; i++){
            arr.add(rand.nextInt(1000,500000));

        }

    }

    static void swap(ArrayList<Integer> arr, int index1, int index2){
        int temp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, temp);
    }

    static int partition(ArrayList<Integer> arr, int left, int right){
        
        int midIndex = (right+left)/2;
        
        // sort them
        if (arr.get(left) > arr.get(midIndex)){
            swap(arr,left, midIndex);
        }

        if (arr.get(left) > arr.get(right)){
            swap(arr,left,right);
        }

        if (arr.get(midIndex) > arr.get(right)){
            swap(arr,midIndex,right);
        }

        // median is now the left
        swap(arr,left,midIndex);
        
        int pivotE = arr.get(left);
        int i = left + 1;


        for (int j = left+1; j <= right; j++){
            if (arr.get(j) < pivotE){
                swap(arr,i,j);
                i++;
            }
        }

        swap(arr, i-1, left);

        return i - 1;
        
    }

    public static void quickSort(ArrayList<Integer> arr, int left, int right){
        if (right - left < 11){
            insertionSort(arr, left, right);
            return;
        }

        if (left<right){
            int pivotI = partition(arr,left,right);
            quickSort(arr,left,pivotI-1);
            quickSort(arr,pivotI+1, right);
        } else {
            return;
        }
    }


    public static void insertionSort(ArrayList<Integer> arr, int left, int right){
        for (int i = left + 1; i< right+1; i++){
            int temp = arr.get(i);
            int j = i -1;

            while (j >= left && arr.get(j) > temp){
                arr.set(j+1,arr.get(j));
                j--;
            }

            arr.set(j+1, temp);
        }
    }


}