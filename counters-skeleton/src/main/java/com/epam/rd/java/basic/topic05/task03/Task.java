package com.epam.rd.java.basic.topic05.task03;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {

	private int numberOfThreads;
	private int numberOfIterations;
	private int pause;
	private int c1;
	private int c2;

	private Thread[] threads;

	public Task(int numberOfThreads, int numberOfIterations, int pause) {
		this.numberOfThreads = numberOfThreads;
		this.numberOfIterations = numberOfIterations;
		this.pause = pause;
		threads = new Thread[numberOfThreads];
		c1 = numberOfThreads;
		c2 = numberOfIterations;

	}

	public void compare() {
		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new Thread(this::inside);
			threads[i].start();
		}
		waiting();
	}

	public void compareSync() {
		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new Thread(() -> {
				synchronized (this) {
					inside();
				}
			});
			threads[i].start();
		}
		waiting();
	}


	public static void main(String[] args) {
		Task t = new Task(2, 5, 10);
		t.compare();
		System.out.println("~~~");
		t.compareSync();
	}

	private void inside() {
		c1 = 0;
		c2 = 0;
		for (int j = 0; j < numberOfIterations; j++) {
			System.out.println((c1 == c2) + " " + c1 + " " + c2);
			c1++;
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, e);
				Thread.currentThread().interrupt();
			}
			c2++;
		}
	}

	private void waiting() {
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, e);
				Thread.currentThread().interrupt();
			}
		}
	}

}