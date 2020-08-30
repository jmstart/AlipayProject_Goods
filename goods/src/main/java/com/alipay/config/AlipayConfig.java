package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102800774395";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDJMgADXXtVE494AldpMuizSq7BuEtSuXiNp8hsPLxPAeHdVU8/V+g0LMlKHJv2WZEMySsETQjt7kA36YLJBDll3hIKcSBOJ+Rz/2MJNxu12m34gBg/y/7yTUDcwoNJ2LRscyYjWPQw7npB34EQnT+20QkTZWadYEea1OvM8HqZvWaFzeOrl75ddo09DF2lX3/FffUicXqtswyR/tHtIT/yQO6sSGzHt+Dqq/nkEdzIxLJU3Ht4hGImvEBgXNx22OzHgzYmoIMY1Osm5Xhs6kIpdNtTHwDvHzzuj/wrLzFvKD+2eZZ2vE2EwCrhmmU/rFCWv100j5EOV3azQTHskvibAgMBAAECggEAU/809DK2B+nXrvHDTp87eYcHeMcZY1sdkn5m9ZFRyOZvRrmUEU0BG69LBk71ah8wqJbgB7alsLgURYOO35+M2xOgko1yQE71WNxZm4JPZlfgK6xG83OWZ9CK8N+gclcv1Esqs/0yTa9yiUfnmWkdLZUN5fQiqDeHDYQxQelwNfiYSeU3a8pSta/jzNGhxFCSPBLt7srVuSGZVaJd3OrTnoUCECVPRSuVPY/DNIgvrBRMFe7Cey54a2C7Rb6NgZ2k9r4kYTCi4iBkXPz4VpJ2Y4j/lKgVCKo6kN9H2OX05LSpAjt80MTZwXSGEx4XQnWMiZzXgP9ZoHnOA5Sx511SIQKBgQDsVXRwZnSve5M0/MF7DJoKJn54yvd7M7x6f4SxP05wSq1ocHtITU27rg1VEQRB6dyy9ZGQYFdACZgPR6dcnzbvczKEMXRUztViaXwulDvj0rhNmHkqVkY3zqRRiihNt0E9HzEP7ry5LutDmaNGQkAsCLG1cLCj0Yrxcu6NCRIm0QKBgQDZ8AA3KiMGf7vnLDDjO8ZrA6cFYyMEJUQj16pm5WAPN/erbH0MRzCXKwn9518dqT9nQQa02L/1FT5AqT0ZXqAQ6wIU6F92ZD2BmO/eHKg4w3tun1IqwpWHv4q6R9MyjI71uhgAu4lUHDZh9L0eB8qFSf3QWsgB0FXu4ZeYmmsbqwKBgCANFtWHj5uc7sHo5XSCqQFnZw+KtzWGNrGGScCgDUs9VXcsigusMDabzEMVI7asuLmshClIZ4zPwEeSOChfj1u6C8fRzS0TP+0w0R6gC99B3KzpWMVdHfjDlZVZX03q5HYdnNHEVuPmcRGh7HrC0WEnbLJmrg4JhQZpx+BosoWBAoGBAJaq8bsxil1n2YQMbQ/rt1UshT+YNhEbomwVIV1Znntm+fatXhYq8VDRFGtQldVvuMGtlvFYF/1b3lndprvvS0Hm9t11eOnkixEZplsr/LChlKf5xViChdX/ee0m0r/TpNIFAasf++UGTCKUjFpCkKRtbueomzWFzqMDm0aJGxtZAoGBAOp+sQejfRk54ecnTuCODdNklQUhoStb3yhbpgAw8TRJ8C+WDZUYeLyrmTJebs+SexGYwTiv1JmULa6AaTnkgwX0zq8I+j/LYvnMm/OecHivFrtU0wIotryOiJY8irXVOJ+7PpYvpZJeHb4a0IOrQ6FCAudj0oJMAEKZoK+U8Krv";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnDiBhZme55JBTjDEPfAbcA/aA8WTW2T3XatWREKsRlcbTn1NaTW3yLt4PGMsfAp5ulH+CLcfa+pLq7ZKk67shC9RXkk+qvU1XiVolR4JcKYYnkS22oMgw48lGpYhbR8R22wT1PmplnpWwDi4JzciGua50+NuXDT33L76Zwi4tzgwLcJSv5PR+5lW+ChknaDspOg0aevFmesY+5KXm7Nfr2AhIuZLuQA/Yb33LmCaIB4ExzSjiPqma97YHe3C5NUcrYSAXdYV4W+Xu2OjJmySWD6pnDC28P1OD40diYUYpP8GoYkcySXH18oEal8+gRwyacrzcpFeHxkRcZ0J5hfoywIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/goods/order/return_url.action";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

