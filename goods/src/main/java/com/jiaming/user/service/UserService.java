package com.jiaming.user.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaming.entity.User;
import com.jiaming.mapper.UserMapper;

import mailhelper.Mail;
import mailhelper.MailUtils;
import povos.ActivationResult;

@Service
public class UserService {

	// 邮箱注册参数
	private static Properties prop = null;
	private static String from = null;
	private static String subject = null;
	private static String content = null;
	private static String host = null;
	private static String username = null;
	private static String password = null;
	static {
		prop = new Properties();

		try {
			prop.load(UserService.class.getClassLoader().getResourceAsStream("email.properties"));
			from = prop.getProperty("from");
			subject = prop.getProperty("subject");
			content = prop.getProperty("content");
			host = prop.getProperty("host");
			username = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Autowired
	private UserMapper userMapper;

	// 验证用户名
	public Boolean getUserByLoginname(String loginname) {
		User u = userMapper.selectUserByLoginname(loginname);
		if (u == null) {
			return true;
		}
		return false;
	}

	// 验证邮箱名
	public Boolean getUserByEmail(String email) {
		User u = userMapper.selectUserByEmail(email);
		if (u == null) {
			return true;
		}
		return false;
	}

	// 验证邮箱注册
	private String getActivationCode() {
		return UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");

	}

	// 发送到邮箱
	public boolean regist(User user) {
		user.setStatus(false);// 新注册的用户 都是 没有激活的
		// 生成 并设置 64位的 激活码
		user.setActivationcode(getActivationCode());
		// 数据库 插入操作
		boolean res = userMapper.insert(user);
		if (res) {
			String to = user.getEmail();
			// 发送 邮件 一共 三步
			// 1 创建 一个 Email 对象
			// from 发件人 2638002006@qq.com
			// to 收件人
			// subject 信封的 封面
			// content 正文
			content = MessageFormat.format(content, user.getActivationcode());
			Mail mail = new Mail(from, to, subject, content);
			// 2 创建一次 Email 的会话 // pop3 smtp 协议
			// host : 邮件服务器的地址 sina smtp.sina.com , qq , smtp.qq.com 163 smtp.163.com

			// username : 2638002006
			// password : 花钱 默认 QQ邮箱 没有开启 smtp 功能的 ，花钱 开启这个功能。给你一个 密码。
			Session session = MailUtils.createSession(host, username, password);

			// 3 把 这个 邮件 发送出去
			try {
				MailUtils.send(session, mail);
			} catch (MessagingException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	// 邮箱中激活
	public ActivationResult activation(String activationCode) {
		ActivationResult ar = new ActivationResult();
		// 拿到 激活码 ， 去数据库 查找 这个用户
		User u = userMapper.seleUserByActivationCode(activationCode);
		if (u == null) {

			ar.setRes(false);
			ar.setMessage("请使用正确的激活码！激活码不是我发给你的！");
		} else {
			if (u.getStatus()) {
				ar.setRes(false);
				ar.setMessage("不要重复的激活。");
			} else {
				u.setStatus(true);
				boolean b = userMapper.updateByPrimaryKey(u);
				if (b) {
					ar.setMessage("激活成功！");
					ar.setRes(true);
				} else {
					ar.setMessage("激活失败了，请重新激活！");
					ar.setRes(false);
				}
			}
		}
		return ar;
	}

	// 登录
	public User login(User user) {

		return userMapper.selectUserByLoginnameAndLoginpass(user);
	}

	// 修改密码
	public boolean changePwd(User u) {

		return userMapper.updateByPrimaryKey(u);

	}
}
