# RAF

The purpose of this exercise is to train you to work with the Java multi-threading.  
The estimated workload is *40 min*.

***

Proceed to `Task` class and implement the following method:  

* `public static void createRAF(int numberOfThreads, int numberOfIterations, int pause)`  
  * creates a random access file `data.txt` with the help of `numberOfThreads` threads (1 <= `numberOfThreads` <= 9);  
  * initially the file *must be truncated* (i.e. all existed content must be removed);  
  * the first thread writes a digit `0` exactly `numberOfIterations` times on the first line of the file;  
  * the second thread writes a digit `1` exactly `numberOfIterations` times on the second line of the file;  
  * and so on...  
  * every line must be terminated with the system default line separator;  
  * all the threads work *simultaneously*;  
  * every thread makes a `pause` when writes an appropriate digit (consider the writing is going on for a `pause` milliseconds);  
  * the **thread-safty** must be guaranteed;  
  * an output file must be readable as the text file in UTF-8 format;  
  * all child threads must be terminated before method ends. 

### Notes

> Don't use **java.util.concurrent** API.

> Don't remove any content of `Task` class.

### Example of an output file content

```
00000000000000000000
11111111111111111111 
22222222222222222222 
33333333333333333333 
44444444444444444444 
55555555555555555555 
66666666666666666666 
77777777777777777777 
88888888888888888888 
99999999999999999999
```
