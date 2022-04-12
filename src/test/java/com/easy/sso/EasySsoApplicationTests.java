package com.easy.sso;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasySsoApplicationTests {

	private static final Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	static {
		for (int i = 0; i < 100; i++) {
			map.put(i, i);
		}
	}

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable1 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 3 == 0) {
						map.remove(i);
					}
				}
			}
		};
		Runnable runnable2 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (i % 3 == 0) {
						map.remove(i);
					}
				}
			}
		};
		runnable1.run();
		runnable2.run();
		while (true) {
			System.out.println(map.toString());
			Thread.sleep(5000l);
		}
	}

}
