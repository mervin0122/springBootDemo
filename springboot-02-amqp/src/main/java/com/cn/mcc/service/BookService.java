package com.cn.mcc.service;

import com.cn.mcc.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by yyzc on 2018/7/2.
 */
@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news")
    public  void receive(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public  void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
