package codetosurvive1.helloworld

import com.rabbitmq.client.ConnectionFactory;

object Send {

  private val QUEUE_NAME="hello";

  def main(argv:Array[String]){

    val factory = new ConnectionFactory();
    factory.setHost("localhost");
    val connection = factory.newConnection();
    val channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);

    val message = "hello world!";
    channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
    println("sent message :"+message);

    channel.close();
    connection.close();
  }
}