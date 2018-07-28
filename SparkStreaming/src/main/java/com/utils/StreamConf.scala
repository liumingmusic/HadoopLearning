package com.utils

import scala.beans.BeanProperty

/**
  * 描述 简单描述方法的作用
  *
  * @author liumm
  * @since 2018-07-28 11:26
  */
class StreamConf {

  @BeanProperty
  var topics: String = "dtinone.*"

  @BeanProperty
  var brokers: String = ""

  @BeanProperty
  var groupId: String = ""

  @BeanProperty
  var zkUrl: String = ""

}
