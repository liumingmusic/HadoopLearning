package com.utils

import java.io.DataOutputStream
import java.net.ServerSocket

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 11:26
  */
object SocketService {

  def main(args: Array[String]): Unit = {

    new Thread(new SparkSocket()).start()

  }

  class SparkSocket extends Runnable {
    override def run(): Unit = {
      val server = new ServerSocket(8880)
      while (true) {
        println("等待客户端进行连接....")
        val socket = server.accept()
        //发送给客户端数据
        val stream = new DataOutputStream(socket.getOutputStream())
        println("发送消息了....")
        stream.writeUTF("The Indian government has decided2 to scrap3 a controversial 12% tax on the feminine")
        //五百毫秒停顿一下
        stream.flush()
        stream.close()
      }
    }

  }

}
