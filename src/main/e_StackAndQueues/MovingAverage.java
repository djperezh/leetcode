package main.e_StackAndQueues;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 346. Moving Average from Data Stream (https://leetcode.com/problems/moving-average-from-data-stream/description/)
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * Implement the MovingAverage class:
 *     MovingAverage(int size) Initializes the object with the size of the window size.
 *     double next(int val) Returns the moving average of the last size values of the stream.
*/
public class MovingAverage {
    double prefix;
    int k;
    Queue<Integer> q;

    public MovingAverage(int size) {
        prefix = 0;
        k = size;
        q = new LinkedList<>();
    }

    public double next(int val) {
        while (!q.isEmpty() && q.size() >= k) prefix -= q.poll();
        q.offer(val);
        prefix += val;
        return prefix / q.size();
    }
}
