package com.epam.rd.java.basic.topic05.task05;

import java.io.*;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {
	public static final String FILE_NAME = "data.txt";
	private static RandomAccessFile raf;
	static final String PATH_TO_FILE = System.getProperty("user.dir");

	public static void createRAF(int numberOfThreads, int numberOfIterations, int pause) throws IOException {
		Thread[] threads = new Thread[numberOfThreads];
		File file = new File(Task.PATH_TO_FILE, FILE_NAME);
		if (file.exists()) {
			try {
				Files.deleteIfExists(Paths.get(file.getPath()));
			} catch (IOException e) {
				Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (IOException e) {
			Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, e);
		}
		for (int i = 0; i < numberOfThreads; i++) {
			final int line = i;
			threads[i] = new Thread(() -> fillLine(line, pause, numberOfIterations));
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			raf.seek(0);
			for (int i = 0; i < raf.length(); i++) {
				System.out.print((char) raf.read());
			}
			raf.close();
		} catch (IOException e) {
			Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private static void fillLine(int line, int pause, int numberOfIterations) {
		for (int j = 0; j < numberOfIterations; j++) {
			try {
				insertValue(line, j, numberOfIterations + 1);
				Thread.sleep(pause);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static synchronized void insertValue(int line, int j, int numberOfIterations) throws IOException {
		raf.seek((long) line * numberOfIterations + j);
		if (j != numberOfIterations) {
			raf.write(String.valueOf(line).getBytes());
		}
		raf.write('\n');
	}


	public static void main(String[] args) throws IOException {
		createRAF(5, 20, 10);

		Files.readAllLines(Paths.get(FILE_NAME))
				.stream()
				.forEach(System.out::println);
	}
}