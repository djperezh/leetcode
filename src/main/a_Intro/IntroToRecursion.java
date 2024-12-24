package main.a_Intro;

public class IntroToRecursion {
    /*
     * NOTE: Recursion is memory intensive due to the call stack generated. 
     * Even thoug the colde looks simple and elegant, Avoid this approach and use iterative solution instead
    */
    public static double fibonacci(double n) {
        if (n < 2) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static double fibonacciIterative(int n) {
        if (n < 2) return n;

        int i = 2;
        double result = 0;
        double prev = 0;
        double curr = 1;

        while (i <= n) {
            result = prev + curr;
            prev = curr;
            curr = result;
            i++;
        }
        
        return result;
    }
}
