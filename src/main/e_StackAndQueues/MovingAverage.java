package main.e_StackAndQueues;

import java.util.LinkedList;
import java.util.Queue;

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
