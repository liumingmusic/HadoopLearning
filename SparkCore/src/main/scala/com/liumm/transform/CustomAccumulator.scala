package com.liumm.transform

import org.apache.commons.lang3.StringUtils
import org.apache.spark.util.AccumulatorV2

/**
  * 自定义累计器
  *
  * @author liumm
  * @since 2018-09-01 19:58
  */
class CustomAccumulator extends AccumulatorV2[String, String] {

  var result = "" //默认值

  override def isZero: Boolean = {
    result == ""
  }

  override def copy(): AccumulatorV2[String, String] = {
    val customAccumulator = new CustomAccumulator()
    customAccumulator.result = this.result
    customAccumulator
  }

  override def reset(): Unit = {
    result = ""
  }

  override def add(v: String): Unit = {
    if (StringUtils.isNoneBlank(v)) {
      if (isZero) {
        result = v
      } else {
        result += "|" + v
      }
    }
  }

  override def merge(other: AccumulatorV2[String, String]): Unit = other match {
    case newAc: CustomAccumulator =>
      if (isZero) result = newAc.value
      else result += "|" + newAc.value
    case _ =>
      throw new UnsupportedOperationException(
        s"Cannot merge ${this.getClass.getName} with ${other.getClass.getName}"
      )
  }

  override def value: String = {
    result
  }
}
