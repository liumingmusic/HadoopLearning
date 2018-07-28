package com.utils

import scala.beans.BeanProperty

/**
  * Spark Streaming 配置
  * @author dtinone
  * 加米谷大数据学院
  * http://www.dtinone.com/
  * 加米谷版权所有，仅供加米谷大数据学院内部使用，禁止其他机构使用，违者必究，追究法律责任。
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
