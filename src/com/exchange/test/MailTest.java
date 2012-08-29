package com.exchange.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exchange.utils.SendMail;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext-mail.xml","classpath*:applicationContext.xml"})
public class MailTest {
	@Resource
	private SendMail sender;
	@Test
	public void testDao() {
		Assert.assertNotNull(sender);
		sender.sendSimpleMessage("wjpgy@o126.com", "请假申请，由于车票不好买，订了明天的车票，所以想请假一天，请2010-2-12这天，请领导批准\n\r请假人：郑建");
	}
}
