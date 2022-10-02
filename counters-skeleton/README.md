# Counters

The purpose of this exercise is to train you to work with the Java multi-threading.  
The estimated workload is *20 min*.

***

Proceed to `Task` class and implement its content.  

* `public Task(int numberOfThreads, int numberOfIterations, int pause)`  
  takes *a number of threads*, *a number of iterations* and *a number of milliseconds as a pause*.  

* `public void compare()`  
  * resets counters to `0`;  
  * creates and runs `numberOfThreades` threads;  
  * each thread performs the following actions `numberOfIterations` times:  
    * compares values of counters and prints out a _comparison result_ and _values of counters_, all are separated with a space character;  
    * increments the first counter;  
    * sleeps for `pause` milliseconds;  
    * increments the second counter;  
  * all threads must be terminated before method ends.  

* `public void compareSync()`  
  works as the `compare` method but guarantees **the thread-safety**.

### Notes

> Don't use **java.util.concurrent** API.

> Don't remove any content of `Task` class.

### Example of an output

```
true 0 0 
true 0 0 
false 2 1 
true 2 2 
false 4 3 
false 4 3 
false 6 4 
false 6 5 
false 8 7 
false 8 7 
~~~
true 0 0 
true 1 1 
true 2 2 
true 3 3 
true 4 4 
true 5 5 
true 6 6 
true 7 7 
true 8 8 
true 9 9 
```