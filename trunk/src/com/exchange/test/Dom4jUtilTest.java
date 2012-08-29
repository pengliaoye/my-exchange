package com.exchange.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class Dom4jUtilTest {
	@Test
	public void createDocument(){
		Document document=DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element root=document.addElement("root");
		document.addElement("hhe");
		Element book=root.addElement("书");
		book.addAttribute("书名", "上帝是个女孩");
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		try {
			XMLWriter xmlWriter=new XMLWriter(new FileWriter(new File("d:\\xml\\x.xml")),format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void readDocument(){
		SAXReader reader=new SAXReader();
		try {
			Document document=reader.read(new File("d:\\xml\\x.xml"));
			Element root=document.getRootElement();
			Node book=root.selectSingleNode("书[@书名='上帝是个女孩']");
			Assert.assertNotNull(book);
			System.out.println(book);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void testXML(){
		File file=null;
		String content="";
		JSONObject jso=null;
		try {
			file=ResourceUtils.getFile("classpath:conf/SysParams_new.xml");
			content = FileUtils.readFileToString(file);
			//jso=XML.toJSONObject(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jso);
	}
	@Test
	public void testCommonCfg(){
		XMLConfiguration config=new XMLConfiguration();
		String fileName="";
		config.setFileName(fileName);
		config.setExpressionEngine(new XPathExpressionEngine());
		config.getProperty("");
		try {
			config.load();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
