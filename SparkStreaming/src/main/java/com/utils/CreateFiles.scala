package com.utils

import java.io.{File, FileOutputStream}
import java.util.concurrent.{Executors, TimeUnit}

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-27 20:27
  */
object CreateFiles {

  def main(args: Array[String]): Unit = {

    val service = Executors.newScheduledThreadPool(1)
    service.scheduleAtFixedRate(new CreateRunnable(), 1, 100, TimeUnit.MILLISECONDS)

  }


  class CreateRunnable extends Runnable {
    override def run(): Unit = {
      val fileName = "/Users/liuxm/A_study/idea_ws/mapreduce/liumm_" + System.currentTimeMillis() + ".txt"
      val file = new File(fileName)
      val stream = new FileOutputStream(file)
      stream.write("The Indian government has decided2 to scrap3 a controversial 12% tax on the feminine\n".getBytes())
      stream.write("hygiene4 products, it announced late Saturday, marking a victory for campaigners who have lobbied against the tax for more than a year.\n".getBytes())
      stream.write("Periods are among the leading factors for girls to drop out of school in India, where four out of five women and girls are estimated by campaigners to have no access to sanitary pads.\n".getBytes())
      stream.flush()
      stream.close()
      println("完成文件 : " + fileName)
    }
  }


}
