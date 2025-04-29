package main.e_StackAndQueues;

import java.util.Stack;

/**
 * 155. Min Stack (https://leetcode.com/problems/min-stack/description/)
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
 *     MinStack() initializes the stack object.
 *     void push(int val) pushes the element val onto the stack.
 *     void pop() removes the element on the top of the stack.
 *     int top() gets the top element of the stack.
 *     int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 * 
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {
    Stack<Integer> s;
    Stack<Integer> minS;
    int min;

    public MinStack() {
        s = new Stack<>();
        minS = new Stack<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        s.push(val);

        // check if the new value is lower than the current minimum
        if (val <= min) {
            minS.push(val);

            // Update min
            min = val;
        }
    }
    
    public void pop() {
        int val = s.pop();

        // check if popped value is the minimum
        if (val == minS.peek()) {
            // Remove the minimum value from minS
            minS.pop();    
            
            // Update min
            min = (!minS.isEmpty()) ? min = minS.peek() : Integer.MAX_VALUE;
        }
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minS.peek();
    }
}
