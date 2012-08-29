package com.exchange.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class ToolTest {

	@Test
	public void testUUID() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/exchange", "root", "");
		System.out.println(conn);
	}

	@Test
	public void testP() {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String enpass = encoder.encodePassword("koala", "rod");
		System.out.println(enpass);

		String a = UUID.randomUUID().toString();
		System.out.println(a);
	}

	@Test
	public void testJSON() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "123");
		map.put("password", null);
		// JSONObject jso = JSONObject.fromObject(map);
		// System.out.println(jso.toString());
	}
}
