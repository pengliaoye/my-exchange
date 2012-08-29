package com.exchange.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import net.sf.json.JSONObject;

import org.apache.catalina.util.Base64;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import com.exchange.domain.User;
import com.exchange.dp.Singleton;

public class OtherTest {
	private static final Logger logger = LoggerFactory
			.getLogger(OtherTest.class);

	@Test
	public void testHtmlUtils() {
		String escapeStr = HtmlUtils.htmlEscape("<input></input>");
		System.out.println(escapeStr);
		System.out.println(HtmlUtils.htmlUnescape(escapeStr));

		String a = null;
		try {
			a.toString();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		System.out.println(DateFormatUtils.format(new Date(),
				"yyyy-MM-dd hh:mm:ss"));
	}

	@Test
	public void testBuilder() throws IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException {
		User user = new User();
		user.setId("1");
		user.setUserName("pgy");
		user.setPassWord("abc");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		System.out.println(user);

		User desuser = (User) BeanUtils.cloneBean(user);

		System.out.println(user.equals(desuser));

		System.out.println(user.hashCode());
	}

	@Test
	public void testTime() {
		Calendar calendar = Calendar.getInstance();

		Timestamp timestamp = new Timestamp(new Date().getTime());
		System.out.println(timestamp);
	}

	@Test
	public void testJSON() {
		JSONObject jso = new JSONObject();
		jso.put("message", "文件上传成功");
		System.out.println(jso.toString());
	}

	@Test
	public void testUpload() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:zte", "admin",
					"abc123456");
			Blob blob = conn.createBlob();
			OutputStream os = blob.setBinaryStream(0);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			InputStream is = new FileInputStream(
					new File(
							"C:\\Documents and Settings\\All Users\\Documents\\My Pictures\\示例图片\\Water lilies.jpg"));
			String sql = "insert into testlob(b) values(?)";
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] b = new byte[4096];
			int i = -1;
			while ((i = bis.read(b)) != -1) {
				bos.write(b);
			}
			bos.flush();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setBlob(1, blob);
			pstmt.executeUpdate();
			bis.close();
			bos.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUUID() {
		Assert.assertEquals(32, UUID.randomUUID().toString()
				.replaceAll("-", "").length());
	}

	@Test
	public void testMonitor() {
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println(totalMemory);
		System.out.println(freeMemory);
	}

	@Test
	public void testEncoding() {
		try {
			System.out
					.println(new String("%E6%96%B9%E4%BE%BF%E8%AE%B0%E5%BF%86"
							.getBytes(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testLength() {
		System.out.println(Long.MIN_VALUE);
		System.out.println(Long.MAX_VALUE);
	}

	@Test
	public void testSingleton() {
		Singleton a = Singleton.getInstance();
		Singleton b = Singleton.getInstance();
		Assert.assertEquals(a, b);
	}

	/**
	 * a.
	 */
	@Test
	public final void testModifyList() {
		try {
			InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * b.
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public final void testother() throws UnsupportedEncodingException {
		String str1 = URLDecoder.decode("%E6%96%B9%E4%BE%AE%B0%E5%BF%86",
				"UTF-8");
		System.out.println(str1);
	}

	@Test
	public void testEncode() {
		try {
			String source = "313100";
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte[] b = md.digest();
			String enstr = Base64.encode(b);
			System.out.println(enstr);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SecureRandom sr = new SecureRandom();
		try {
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(1);

			DESKeySpec dks = new DESKeySpec("gx10000#".getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			byte[] bt = cipher.doFinal("admin".getBytes());
			String ens = Base64.encode(bt);
			System.out.println(ens);
		} catch (Exception e) {
		}
	}

	@Test
	public void testProp() {
		System.out.println(System.getProperty("java.io.tmpdir"));
	}

	@Test
	public void testFTP() {
		StaticUserAuthenticator auth = new StaticUserAuthenticator(null,
				"anonymous", null);
		FileSystemOptions opts = new FileSystemOptions();
		FileObject fo = null;
		InputStream is = null;
		File file = null;
		FileOutputStream os = null;
		String fileName = null;
		try {
			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(
					opts, auth);
			fo = VFS.getManager().resolveFile(
					"ftp://admin:admin@localhost:2121/README.txt", opts);
			fileName = fo.getName().getBaseName();
			is = fo.getContent().getInputStream();
			file = new File("d:" + File.separator + fileName);
			try {
				os = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// IOUtils.copy(is, response.getOutputStream());
			try {
				IOUtils.copy(is, os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileSystemException e) {
			logger.error("ftp access failure", e);
			e.printStackTrace();
		} finally {
			try {
				if (fo != null) {
					fo.close();
					VFS.getManager().closeFileSystem(fo.getFileSystem());
				}
			} catch (FileSystemException e) {
				logger.error("close filesystem error", e);
			}
			IOUtils.closeQuietly(os);
		}
	}

	@Test
	public void testFTP1() {
		StaticUserAuthenticator auth = new StaticUserAuthenticator(null,
				"admin", "admin");
		FileSystemOptions opts = new FileSystemOptions();
		FileObject remoteFile = null;
		FileObject localFile = null;
		String fileName = null;
		try {
			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(
					opts, auth);
			localFile = VFS.getManager().resolveFile("file://d:/zxcc.log");
			// fileName=localFile.getName().getBaseName();
			fileName = "";
			remoteFile = VFS.getManager().resolveFile(
					"ftp://localhost:2121/" + fileName, opts);
			remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
		} catch (FileSystemException e) {
			logger.error("ftp access failure", e);
			e.printStackTrace();
		} finally {
			try {
				if (localFile != null) {
					localFile.close();
					VFS.getManager().closeFileSystem(localFile.getFileSystem());
				}
				if (remoteFile != null) {
					remoteFile.close();
					VFS.getManager()
							.closeFileSystem(remoteFile.getFileSystem());
				}
			} catch (FileSystemException e) {
				logger.error("close filesystem error", e);
			}
		}
	}

	@Test
	public void testFtp() {
		FTPClient client = new FTPClient();
		client.addProtocolCommandListener(new PrintCommandListener(
				new PrintWriter(System.out)));
		String ip = "127.0.0.1";
		int port = 2121;
		String username = "admin";
		String password = "admin";
		boolean storeFile = false;
		int reply;
		String local = "d:" + File.separator + "zxcc.log";
		String remote = "zxcc.log";
		try {
			client.connect(ip, port);
			logger.debug("Connected to " + ip + ".");
			reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.error("FTP server refused connection.");
				client.disconnect();
			}
		} catch (IOException e) {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException e1) {
					// do nothing
				}
			}
			logger.error("Could not connect to server.");
		}
		try {
			if (!client.login(username, password)) {
				client.logout();
			}
			client.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.enterLocalPassiveMode();
		client.setBufferSize(4096);
		try {
			if (storeFile) {
				InputStream input = new FileInputStream(local);
				client.changeWorkingDirectory(File.separator + "book");
				client.storeFile(remote, input);
				input.close();
			} else {
				OutputStream output = new FileOutputStream(local);
				client.retrieveFile(remote, output);
				output.close();
			}
		} catch (FileNotFoundException fnfe) {
			logger.error("file path is error", fnfe);
		} catch (FTPConnectionClosedException c) {
			logger.error("Server closed connection.", c);
		} catch (IOException e) {
			logger.error("ftp upload/download error.", e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException e1) {
					// do nothing
				}
			}
		}
	}

	@Test
	public void testFis() {
		try {
			long space = FileSystemUtils.freeSpaceKb("c:");
			System.out.println(space / 1024.0 / 1024.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOldSoap() {
		int type = 1;
		// 07713820581 18978680252
		StringBuilder builder = new StringBuilder();
		if (type == 1) {
			builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			builder.append("<inputInfo>");
			builder.append("<method>getBillInfo</method><beginDate>2011-03-25 00:00:00</beginDate><endDate>2011-04-09 23:59:59</endDate><custNo>07713820581</custNo>");
			builder.append("</inputInfo>");
		} else {
			builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			builder.append("<inputInfo>");
			builder.append("<method>getBillCount</method><custNo>07713820581</custNo>");
			builder.append("</inputInfo>");
		}
		String request = builder.toString();
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage();
			// Create objects for the message parts
			SOAPPart part = message.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			SOAPBody body = envelope.getBody();
			// SOAPElement bodyElement =
			// body.addChildElement(envelope.createName(
			// "service", "tns", "http://ws.exchange.com"));
			SOAPElement bodyElement = body.addChildElement(envelope
					.createName("service"));
			bodyElement.addChildElement("request").addTextNode(request);
			message.saveChanges();

			System.out.println("REQUEST:");
			message.writeTo(System.out);

			// Now create the connection
			SOAPConnectionFactory connFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection conn = connFactory.createConnection();
			SOAPMessage reply = conn.call(message,
					"http://134.201.138.130:9082/zxcc/services/NetWebService");
			SOAPPart partEnd = reply.getSOAPPart();
			SOAPEnvelope replyEnv = partEnd.getEnvelope();
			System.out.println("\nRESPONSE:");
			reply.writeTo(System.out);

			conn.close();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
