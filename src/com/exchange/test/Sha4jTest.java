package com.exchange.test;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import com.softabar.sha4j.ShaUtil;

public class Sha4jTest {
	@Test
	public void testSha4j() {
		String encodeStr = "";
		try {
			encodeStr = ShaUtil
					.toSha512String("122222222123333333333313121312");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(encodeStr);
	}

	@Test
	public void testRondom() {   // 8位随机数生成
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 8; i++) {
			result = result * 10 + array[i];
		}
		System.out.println(result);
	}
}
