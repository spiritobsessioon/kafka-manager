/**
 * Copyright 2015 Yahoo Inc. Licensed under the Apache License, Version 2.0
 * See accompanying LICENSE file.
 */

package kafka.manager

import java.nio.charset.StandardCharsets

/**
 * @author hiral
 */
package object utils {
  import org.json4s._
  import org.json4s.jackson.JsonMethods._
  import org.json4s.jackson.Serialization.{read, write}
  implicit val formats = DefaultFormats

  implicit def serializeString(data: String) : Array[Byte] = {
    data.getBytes(StandardCharsets.UTF_8)
  }

  implicit def deserializeString(data: Array[Byte]) : String  = {
    new String(data, StandardCharsets.UTF_8)
  }

  def toJson(map: Map[String, Any]): String = {
    write(map)
  }

  def fromJson[T](s: String) : T = {
    read(s)
  }

  def parseJson(s: String) : JValue = {
    parse(s)
  }

  @throws[UtilException]
  def checkCondition(cond: Boolean, error: UtilError) : Unit = {
    if(!cond) {
      throw new UtilException(error)
    }
  }

  @throws[UtilException]
  def throwError [T] (error: UtilError) : T = {
    throw new UtilException(error)
  }
}
