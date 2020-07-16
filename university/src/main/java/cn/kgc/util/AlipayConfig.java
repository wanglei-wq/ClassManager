package cn.kgc.util;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101200666901";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCngIagLe/nr75NiIjrr3DwDc8xUpknKiNMQ6nq/ztUV8IXoTLthuDVdK11Jlk/ro5HACLJarYWqC4MBfecYeFYWo2DgegL1Lp4+hl6xHkQOFCrRWGmhaOXoQA0TbqGG6+oHvbgm3Dl+W1iGxmq2o3VPckYATY9s4Qska2i71rf8n2E4WKpMwOH1WMqRziRC6NPcGJX93GUnMFaZRr46Ztbuz09OKJiyIxfKaNVGgii+SsZ1dgfvXvI16TBAcej4V8Hc+MBPNWfYyjjxZcnqEK9bvPHg9aZg8ZGrNAArgwnePE6QPglYkusDlGZp/n25c6jsRi1uBrylRUgiX8eAFLHAgMBAAECggEAJRxct3B2DqbpStRNQaqFIypsFUlvlTXIzETRiLczteQbpJCab/XKKoDHTYVJkMpjy2dETcvOvAZW7WMFESi+fIzTfadyeeyPw+3lZ0XQbL+NX+TnVbWWISoSmYP/J2dK67ywheLqiIlSqsHGVgxGEFC4FRFvqwvy5M0B42+UerkrG2bj7VXfhRDoXtttjbYTOQ7P0QNhoxQ+sBfD1pHM50QmXWisNIVvo/P7sp/LarJ1LauHq22tzpXEHd2Z1qyV2xIQqrqJlFqVaxN0VPp/PBmCNfEYhXV78o1qnECPL4KJdamuCjL85//rDfM/1cR3CwngU1josEo8yBEhCkaLgQKBgQDbmcUoXnm369wqwh6lZ69E5WAWxfrUUKb8/jYsZiZA9cHiEuRO8r5fPg02i0vpuczl7jPUE2Sw7/4ez5Th7e2rIUE7fkXzTu6OGUqE4JAiigROc5hVqET/SyY2ZxyTemhe2WVcGQxrLU//a0e862uXRZU/SAxI4+2+YStr84kZvwKBgQDDRBNahGFWawXRln71c3zLCSDsXeJVN7numQB1TWjhgJ0DH+itwdFm7x3Xcbr5EPxRBCCU6/PsDxb41QtwbOrgwhddzPUdktCtTzDACi7CkgvARwbAuJReLx/3yixr1uzermDVYsdvdsJ/osT8JTaR7a2h56eotZn9tNNoBBu4+QKBgGsTECn93tWKAIEsw6qc4r3yJacVA5eQd8u13A5MBGz6J34m/gFspNxlsiWdR/d/q+OVANpwVlFbw4mcZ5i93XvqrBbpHAxluTW6KTafFEhzCWSDQozJjlL4fnpaTnmuXsOYtq5wXDjKEE6XLZR1KbHJsNr5M1w7HsoMMRLi3T+bAoGANRmoIa9qQPPtqyc/80OqphQfbefCCuaSm33kEAIzCU/OYvEkd0wDw/g2p0X4UI777pQuwBDCTml0F8rnx3T3EpP1gr6f0aEBOoAUg8TBHoxUwb8O6Q3I2yv5h4rlNZZG5++Hyb+I+JjVH5yF8QO1qU13MFYs08Y+0C2CaADmARECgYEAp97a/RtdfeWpywIs+PnH5OADwz6XtDqFj/FW6Gg1ZuyyXXsZaHNTL+FYsk1L1x9jJxZmmVfBL3ZOw48GT5BsMOitAHGzBxElqsC23EXUqsHgIPge+BxhSowQzoBGfHLGnXfreuVK2Ayn2fKCjz2k5P1y3iWJkDQ3+wXVXdUMJIw=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArNxegv8HSrktx/O70dlBKBgi+B9l+6oBL2qemizYzq16LftXVZcJAOqNkg6Rp1hr6fgGuTkthcZ8xP8/8lz5ne+A41w3V0v7v2KiRm+TBQSVz9B6uh4NKWRi44s5hxg89b6caEcyEtXJRa8NR3dSO87t8Bc+3hWECtxNVRWdBTu7FuracIW8TI0jqNmfjE8Riib/TMlkQk4SksVdm0NBk3H5UIWDk6RhQIHSLx9oocLw1XF6f/3A4dbWaGQ8Lg+dnWo4yhdCgM1vZL565MKZtrcpfwsOtp/mtI5UnoiZeFTsZdT8gHvJCUkuiEn6xz2Gk5+Iu+BUvjFZwj0KpzaPTQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay/callBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipay/callBack";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    //https://openapi.alipaydev.com/gateway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

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
