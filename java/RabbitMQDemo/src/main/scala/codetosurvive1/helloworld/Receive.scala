package codetosurvive1.helloworld;

import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client.{ConnectionFactory, DefaultConsumer, Envelope};

/**
  * Created by mac on 2017/1/8.
  */
object Receive {

  private val QUEUE_NAME="hello";

  def main(args:Array[String]){

    val factory = new ConnectionFactory();
    factory.setHost("localhost");

    val connection = factory.newConnection();
    val channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME,false,false,false,null);

    println("waiting for messages.");

    val consumer = new DefaultConsumer(channel){
      override def handleDelivery(consumerTag: String,
                                  envelope: Envelope,
                                  properties: BasicProperties,
                                  body: Array[Byte]): Unit = {
        var message = new String(body,"UTF-8");
        println("received message :"+message);
      }
    }

    channel.basicConsume(QUEUE_NAME,true,consumer);
  }

}
