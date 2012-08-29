package com.exchange.dp;

import org.junit.Test;

public class DesignPatternTest {
	@Test
	public void testDynamicProxy() {
		Foo foo = (Foo) DynamicProxy.newInstance(new FooImpl());
		System.out.println(foo.bar(null));
	}
}
