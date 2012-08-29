package com.exchange.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:applicationContext-freemarker.xml"})
public class SpringTest {
	@Autowired
	private Configuration configuration;
	//@Resource
	//private ThreadLocal threadLocal;

	@Test
	public void testFreeMarker() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("message", "freemarker");
		Template template = null;
		String result = "";
		try {

			configuration.setDirectoryForTemplateLoading(new File(System
					.getProperty("user.dir")
					+ "/WebContent" + "/WEB-INF/templates"));
			template = configuration.getTemplate("test.ftl");
			result = FreeMarkerTemplateUtils.processTemplateIntoString(
					template, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}
	@Test
	public void testThreadLocal(){
		//Assert.isInstanceOf(ThreadLocal.class, threadLocal);
	}
}
