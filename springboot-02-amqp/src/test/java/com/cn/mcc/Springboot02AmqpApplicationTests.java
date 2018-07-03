package com.cn.mcc;

import com.cn.mcc.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {
	@Autowired
	RabbitTemplate rabbitTemplate;
	//系统管理组件
	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public  void createExchange(){
		//1.创建ExChange
		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
		//System.out.println("创建完成");
		//2.创建Queue
		amqpAdmin.declareQueue(new Queue("mervin.queue",true));
		//3.创建绑定规则
		amqpAdmin.declareBinding(new Binding("mervin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.hah",null));
	}

	/**
	 * 1.单播（点对点）
	 */
	@Test
	public void contextLoads() {
		//Message需要自己构造；定义消息体内容和消息头
		//rabbitTemplate.send(exchange,routekey,message);
		//object默认成消息体，只需传入要发送的对象，自动序列化发给rabbitmq
		//rabbitTemplate.convertAndSend(exchange,routekey,message);
		Map<String,Object> map=new HashMap<>();
		map.put("msg","这是第一个消息");
		map.put("data", Arrays.asList("helloWorld",123,true));
		//对象被默认序列化后以后发送出去
		//rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
		//自定义对象
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("Spirngboot","张三"));
	}
	//2.接收数据，如何将数据自动转换为json发送出去
	@Test
	public void receive() {
		Object o=rabbitTemplate.receiveAndConvert("atguigu.emps");
		System.out.println(o.getClass());
		System.out.println(o);
	}
	/**
	 *3. 广播
	 */
	@Test
	public void sendMsg() {
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("Java基础教程","的卡夫卡"));
	}

}
