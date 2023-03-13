/*
The goal is to sort numbers by their Collatz length.
It is conjectured that any number can be reduced to 1 by following these steps:
1) If the number is even divide it in 2
2) If the number is odd, multiply by 3 and add 1

Input Format
An integer a which indicates the start of the range
An integer b which indicates the end of the range, inclusive
A target integer x

Output Format
An integer which is the xth number in the list that results when the numbers from a to b inclusive are sorted according to Collatz length  */

import java.util.*;

public class mergeSortCollatz{

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        int start = sc.nextInt();
        int finish = sc.nextInt();
        int target = sc.nextInt();
        int count = 0; 
        int indexCount = 0;
        long[] values = new long[(finish-start)+1];
        long[] collatz = new long[(finish-start)+1];
        long temp = 0;

        for(int x=start;x<=finish;x++){
            count = 0;
            values[indexCount] = x;
            temp = x;

            while(temp != 1){

                if(temp % 2 != 0){
                    temp = 3 * temp + 1;
                    count++;
                }
                else if(temp % 2 == 0){
                    temp = temp/2;
                    count++;
                }  
            }
            collatz[indexCount] = count;
            indexCount++;
        }   

        mergeSort(collatz, values);

        System.out.println(values[target-1]);
    }

    private static void mergeSort(long[] collatz, long[] values){

        int inputLength = collatz.length;

        if(inputLength<2){
            return;
        }

        int midIndex = inputLength / 2;
        long[] collatzLH = new long[midIndex];
        long[] collatzRH = new long[inputLength - midIndex];
        long[] valuesLH = new long[midIndex];
        long[] valuesRH = new long[inputLength - midIndex];

        for(int i=0;i<midIndex;i++){
            collatzLH[i] = collatz[i];
            valuesLH[i] = values[i];
        }
        for(int i=midIndex;i<inputLength;i++){
            collatzRH[i-midIndex] = collatz[i];
            valuesRH[i-midIndex] = values[i];
        }
        mergeSort(collatzLH, valuesLH);
        mergeSort(collatzRH, valuesRH);

        merge(collatz, values, collatzLH, collatzRH, valuesLH, valuesRH);
    }

    private static void merge(long[] collatz, long[] values, long[] collatzLH, long[] collatzRH, long[] valuesLH, long[] valuesRH){
        int leftSize = collatzLH.length;
        int rightSize = collatzRH.length;

        int i = 0, j = 0, k = 0;

        while(i < leftSize && j < rightSize){
            if(collatzLH[i] <= collatzRH[j]){
                collatz[k] = collatzLH[i];
                values[k] = valuesLH[i];
                i++;
            }
            else{
                collatz[k] = collatzRH[j];
                values[k] = valuesRH[j];
                j++;
            }
            k++;
        }
        while(i < leftSize){
            collatz[k] = collatzLH[i];
            values[k] = valuesLH[i];
            i++;
            k++;
        }
        while(j < rightSize){
            collatz[k] = collatzRH[j];
            values[k] = valuesRH[j];
            j++;
            k++;
        }
    }
    
}
