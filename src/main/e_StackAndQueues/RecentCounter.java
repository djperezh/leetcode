package main.e_StackAndQueues;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 933. Number of Recent Calls
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * Implement the RecentCounter class: 
 * RecentCounter() Initializes the counter with zero recent requests. 
 * int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). 
 * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t]. 
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
*/
public class RecentCounter {
    Queue<Integer> queue;
    int window; // time frame
    
    public RecentCounter(int window) {
        this.queue = new LinkedList<>();
        this.window = window;
    }

    public int ping(int t) {
        while (!queue.isEmpty() && queue.peek() < t - window) queue.poll();
        this.queue.offer(t);
        return queue.size();
    }
}
