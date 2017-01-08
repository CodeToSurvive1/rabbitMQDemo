package io.github.codetosurvive1.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by mac on 2017/1/8.
 */
public class Recv {

    private static final String QUEUE_NAME="hello";

    public static void main(String args[]) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        System.out.println("waiting for messages.To exit press CTRL+C");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");

                System.out.println("received:'"+message+"'");
            }
        };

        channel.basicConsume(QUEUE_NAME,consumer);

    }

}
