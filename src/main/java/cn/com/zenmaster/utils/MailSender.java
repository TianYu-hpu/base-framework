package cn.com.zenmaster.utils;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;
import java.util.Date;

public class MailSender {


	   private static String host ="smtp.gzu.edu.cn";
	   private static int port =25;
	   private static String userName ="caecw2018@gzu.edu.cn";
	   private static String password ="Caecw@2019";
	   private String to = "";
	   private String content = "";
	   private String title = "";
	   private String Charset="UTF-8";
	   private String filePath="";

	   private  boolean nolog=false;
	   

	public boolean isNolog() {
		return nolog;
	}

	public void setNolog(boolean nolog) {
		this.nolog = nolog;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	    * 发送文本邮件
	    * 
	    * @throws Exception
	    */
	   public void sendTextMail() throws Exception
	   {
	      SimpleEmail mail = new SimpleEmail();
	      // 设置邮箱服务器信息
	      mail.setSmtpPort(port);
	      mail.setHostName(host);
	      // 设置密码验证器
	      mail.setAuthentication(userName, password);
	      // 设置邮件发送者
	      mail.setFrom(userName);
	      // 设置邮件接收者
	      mail.addTo(to);
	      // 设置邮件编码
	      mail.setCharset(Charset);
	      // 设置邮件主题
	      mail.setSubject(title);
	      // 设置邮件内容
	      mail.setMsg(content);
	      // 设置邮件发送时间
	      mail.setSentDate(new Date());
	      // 发送邮件
	      mail.send();
	   }

	   /**
	    * 发送Html邮件
	    * 
	    * @throws Exception
	    */
	   public void sendHtmlMail() throws Exception
	   {
	      HtmlEmail mail = new HtmlEmail();
	      // 设置邮箱服务器信息
	      mail.setSmtpPort(port);
	      mail.setHostName(host);
	      // 设置密码验证器
	      mail.setAuthentication(userName, password);
	      // 设置邮件发送者
	      mail.setFrom(userName);
	      // 设置邮件接收者
	      mail.addTo(to);
	      // 设置邮件编码
	      mail.setCharset(Charset);
	      // 设置邮件主题
	      mail.setSubject(title);
	      // 设置邮件内容
	      mail.setHtmlMsg(
	            "<html><body>"+content+"</body></html>");
	      // 设置邮件发送时间
	      mail.setSentDate(new Date());
	      try {
		      // 发送邮件
		      mail.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }

	   /**
	    * 发送内嵌图片邮件
	    * 
	    * @throws Exception
	    */
	   public void sendImageMail() throws Exception
	   {
	      HtmlEmail mail = new HtmlEmail();
	      // 设置邮箱服务器信息
	      mail.setSmtpPort(port);
	      mail.setHostName(host);
	      // 设置密码验证器
	      mail.setAuthentication(userName, password);
	      // 设置邮件发送者
	      mail.setFrom(userName);
	      // 设置邮件接收者
	      mail.addTo(to);
	      // 设置邮件编码
	      mail.setCharset(Charset);
	      // 设置邮件主题
	      mail.setSubject(title);
	      mail.embed(new File("1_jianggujin.jpg"), "image");
	      // 设置邮件内容
	      String htmlText = "<html><body>"+content+"</body></html>";
	      mail.setHtmlMsg(htmlText);
	      // 设置邮件发送时间
	      mail.setSentDate(new Date());
	      // 发送邮件
	      mail.send();
	   }

	   /**
	    * 发送附件邮件
	    * 
	    * @throws Exception
	    */
	   public void sendAttachmentMail() throws Exception
	   {
		   HtmlEmail mail = new HtmlEmail();
	      // 设置邮箱服务器信息
	      mail.setSmtpPort(port);
	      mail.setHostName(host);
	      // 设置密码验证器
	      mail.setAuthentication(userName, password);
	      // 设置邮件发送者
	      mail.setFrom(userName);
	      // 设置邮件接收者
	      mail.addTo(to);
	      // 设置邮件编码
	      mail.setCharset(Charset);
	      // 设置邮件主题
	      mail.setSubject(title);

	      mail.setHtmlMsg(content);
	      
	      // 创建附件
	      EmailAttachment attachment = new EmailAttachment();
	      attachment.setPath(filePath);
	      attachment.setDisposition(EmailAttachment.ATTACHMENT);
	      attachment.setName(new File(filePath).getName());
	      mail.attach(attachment);

	      // 设置邮件发送时间
	      mail.setSentDate(new Date());
	      // 发送邮件
	      mail.send();
	   }

	   /**
	    * 发送内嵌图片和附件邮件
	    * 
	    * @throws Exception
	    */
	   public void sendImageAndAttachmentMail() throws Exception
	   {
	      HtmlEmail mail = new HtmlEmail();
	      // 设置邮箱服务器信息
	      mail.setSmtpPort(port);
	      mail.setHostName(host);
	      // 设置密码验证器
	      mail.setAuthentication(userName, password);
	      // 设置邮件发送者
	      mail.setFrom(userName);
	      // 设置邮件接收者
	      mail.addTo(to);
	      // 设置邮件编码
	      mail.setCharset("UTF-8");
	      // 设置邮件主题
	      mail.setSubject("Test Email");
	      mail.embed(new File("1_jianggujin.jpg"), "image");
	      // 设置邮件内容
	      String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
	      mail.setHtmlMsg(htmlText);
	      // 创建附件
	      EmailAttachment attachment = new EmailAttachment();
	      attachment.setPath("1_jianggujin.jpg");
	      attachment.setDisposition(EmailAttachment.ATTACHMENT);
	      attachment.setName("1_jianggujin.jpg");
	      mail.attach(attachment);
	      // 设置邮件发送时间
	      mail.setSentDate(new Date());
	      // 发送邮件
	      mail.send();
	   }
	   

	public String getHost() {
		return host;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCharset() {
		return Charset;
	}

	public void setCharset(String charset) {
		Charset = charset;
	}


    /*public static void main(String[] args){
        MailSender mailSender = new MailSender();

        mailSender.setTitle("ceshi");
        mailSender.setContent("sdfssd");
        mailSender.setTo("105780873@qq.com");

        try {
            mailSender.sendHtmlMail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

	   
}
