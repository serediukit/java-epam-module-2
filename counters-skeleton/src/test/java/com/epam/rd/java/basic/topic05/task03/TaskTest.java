package com.epam.rd.java.basic.topic05.task03;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

/**
 * @author D. Kolesnikov
 */
public class TaskTest {

	private static final PrintStream STD_OUT = System.out;

	private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	private final PrintStream out = new PrintStream(baos);
	
	@ParameterizedTest
	@CsvSource({"3,3", "3,5", "5,3", "5,5"})
	void shouldCountersBeEqualedForEveryIterationWhenSync(int n, int k) {
		System.setOut(out);
		Task t = new Task(n, k, 1);

		t.compareSync();

		out.flush();
		String actual = baos.toString();
		System.setOut(STD_OUT);

		for (String line : actual.split(System.lineSeparator())) {
			if ("false".equals(line.substring(0, line.indexOf(' ')))) {
				Assertions.fail(() -> "Not synchronized code detected: " + line);
			}
		}
	}

	@ParameterizedTest
	@CsvSource({"7,7", "7,5", "5,7", "5,5"})
	void shouldCountersBeNotEqualedForSomeIterationWhenNotSync(int n, int k) {
		System.setOut(out);
		Task t = new Task(n, k, 2);

		t.compare();

		out.flush();
		String actual = baos.toString();
		System.setOut(STD_OUT);

		for (String line : actual.split(System.lineSeparator())) {
			if ("false".equals(line.substring(0, line.indexOf(' ')))) {
				return;
			}
		}
		Assertions.fail(() -> "Synchronized code detected:\n" + actual);
	}

	@Test
	void shouldCountersBeReseted() {
		Task t = new Task(5, 5, 1);

		t.compare();
		System.setOut(out);
		t.compareSync();

		out.flush();
		String actual = baos.toString();
		System.setOut(STD_OUT);

		for (String line : actual.split(System.lineSeparator())) {
			if ("false".equals(line.substring(0, line.indexOf(' ')))) {
				Assertions.fail(() -> "It looks like counters not reseted");
			}
		}
	}

	@ParameterizedTest
	@CsvSource({"2,5,10", "4,5,5", "3,7,7"})
	void shouldBeNWorkingThreadsWhenCompareKTimes(int n, int k, int pause) throws InterruptedException {
		List<String> threadNames = new ArrayList<>();
		Task t = new Task(n, k, pause);
		Thread detector = startThreadsDetector(threadNames, k * pause / 2);

		t.compareSync();

		detector.join();
		long threadsCount = threadNames.size();
		Assertions.assertTrue(threadsCount == n, 
				() -> "'compare' must use " + n + " threads, detected threads: " + threadNames);
	}

	@ParameterizedTest
	@CsvSource({"2,5,10", "4,5,5", "3,7,7"})
	void shouldBeNWorkingThreadsWhenCompareSyncKTimes(int n, int k, int pause) throws InterruptedException {
		List<String> threadNames = new ArrayList<>();
		Task t = new Task(n, k, pause);
		Thread detector = startThreadsDetector(threadNames, k * pause / 2);

		t.compareSync();

		detector.join();
		long threadsCount = threadNames.size();
		Assertions.assertTrue(threadsCount == n, 
				() -> "'compareSync' must use " + n + " threads, detected threads: " + threadNames);
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
