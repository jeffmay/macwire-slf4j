package macwire.contrib.slf4j

import org.slf4j.LoggerFactory

import scala.language.experimental.macros
import scala.reflect.ClassTag
import scala.reflect.macros.blackbox

object WireWithLogger {

  def wireWithClassLogger[A](implicit tag: ClassTag[A]): A = macro WireWithLoggerMacros.wireWithClassLoggerImpl[A]
}

object WireWithLoggerMacros {

  def wireWithClassLoggerImpl[A: c.WeakTypeTag](c: blackbox.Context)(tag: c.Expr[ClassTag[A]]): c.Expr[A] = {
    import c.universe._
    reify {
      val logger = LoggerFactory.getLogger(tag.splice.runtimeClass)
      com.softwaremill.macwire.MacwireMacros.wire_impl[A](c).splice
    }
  }
}
