package cn.kgc.controller;

import cn.kgc.service.ClazzFeeService;
import cn.kgc.util.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2020/5/11.
 */
@Controller
@RequestMapping("alipay")
public class AlipayController {
    @Autowired
    public ClazzFeeService clazzFeeService;

    @RequestMapping(value = "/payFee",produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String alipay(@RequestParam("fid")Integer fid,
                          @RequestParam("num")String num,
                          @RequestParam("money")Double money,
                          HttpServletResponse response) throws IOException {
        System.out.println("fid:" + fid + "num:" + num + "money:" + money);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        //商品描述，项目生成的订单号16位随机数
        String out_trade_no = fid + "-"+num;
        //订单名称，必填
        String subject = "支付宝第三方测试";

        String body = "";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ money +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result=null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);
        //response.getWriter().println(result);
        return result;
    }
    @RequestMapping(value = "toPay")
    public String toPay(){
        return "pay_success";
    }
    @RequestMapping(value = "callBack")
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        /**
         * 支付宝的回调 告诉你 1.支付宝订单号 2.自己商城生成的订单号 3.付款金额
         */
        // 商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        // 支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        // 付款金额
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
        //设置相应的编码格式是 html
        response.setContentType("text/html;charset=utf-8");
        System.out.println("订单号:"+out_trade_no);
        System.out.println("支付宝交易号"+trade_no);
        System.out.println("付款金额"+total_amount);
        String[] split = out_trade_no.split("-");
        int fid = Integer.parseInt(split[0]);
        String num = split[1];
        double money = Double.parseDouble(total_amount);
        Integer i = clazzFeeService.payFee(fid, num,money);
        return "pay_success";
    }
}
