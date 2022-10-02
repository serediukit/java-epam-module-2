package com.epam.rd.java.basic.topic05.task05;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


/**
 * @author D. Kolesnikov
 */
public class TaskTest {

	@ParameterizedTest
	@ValueSource(ints = {7, 5, 3, 1})
	void shouldBeNValues(int n) throws IOException {
		String expected = String.format("%0" + n + "d", 0);
		Task.createRAF(1, n, 1);
		List<String> lines = Files.readAllLines(Paths.get(Task.FILE_NAME));
		Assertions.assertEquals( "[" + expected + "]", lines.toString());
	}	

	@ParameterizedTest
	@ValueSource(ints = {1, 3, 5, 7})
	void shouldBeNLines(int n) throws IOException {
		List<String> expected = new ArrayList<>();
		for (int j = 0; j < n; j++) {
			expected.add(String.format("%05d", 0).replace('0', (char)('0' + j)));
		}
		
		Task.createRAF(n, 5, 1);
		List<String> lines = Files.readAllLines(Paths.get(Task.FILE_NAME));
		Assertions.assertIterableEquals(expected, lines);
	}	
		
	@ParameterizedTest
	@CsvSource({"3,5,10", "5,5,10", "7,7,7"})
	void shouldBeNWorkingThreads(int n, int k, int pause) throws InterruptedException, IOException {
		List<String> threadNames = new ArrayList<>();
		Thread t = startThreadsDetector(threadNames, k * pause / 2);
		
		Task.createRAF(n, k, pause);

		t.join();
		long threadsCount = threadNames.size();
		Assertions.assertTrue(threadsCount == n,
				() -> "'createRAF' must use exactly " + n + " threads, detected threads are: " + threadNames);
	}
	
	private Thread startThreadsDetector(List<String> threadNames, int delay)  {
		Thread t = new Thread() {
			public void run() {
				setName("threads-detector");
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Thread.getAllStackTraces().keySet().stream()
					.map(t -> t.getName())
					.filter(name -> name.startsWith("Thread-"))
					.forEach(threadNames::add);
			}
		};
		t.start();
		return t;
	}
	
}
