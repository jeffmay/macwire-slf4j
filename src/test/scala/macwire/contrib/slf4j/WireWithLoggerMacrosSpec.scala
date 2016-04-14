package macwire.contrib.slf4j

import org.scalatest.WordSpec
import org.slf4j.{LoggerFactory, Logger}
import com.softwaremill.macwire.wire
import WireWithLogger.wireWithClassLogger

class WireWithLoggerMacrosSpec extends WordSpec {

  val defaultLogger: Logger = LoggerFactory.getLogger(getClass)

  "MacwireMacros" should {

    "wire the instance in class-level scope" in {
      class Example {
        val logger: Logger = defaultLogger
        val thing: ThingWithLogger = wire[ThingWithLogger]
      }
      val example = new Example
      assert(example.thing.logger === defaultLogger)
    }

    "wire the instance in local block scope" in {
      val thing: ThingWithLogger = {
        val logger = LoggerFactory.getLogger(getClass)
        wire[ThingWithLogger]
      }
      assert(thing.logger !== defaultLogger)
    }
  }

  "WireWithLogger.wireWithClassLogger" should {

    /*
       THE FOLLOWING DO NOT COMPILE
     */

    "wire the instance with a logger in class-level scope" in {
      class Example {
        val thing: ThingWithLogger = wireWithClassLogger[ThingWithLogger]
      }
      val example = new Example
      assert(example.thing.logger !== defaultLogger)
    }

    "wire the instance with a logger in local block scope" in {
      val thing: ThingWithLogger = wireWithClassLogger[ThingWithLogger]
      assert(thing.logger !== defaultLogger)
    }
  }

}

class ThingWithLogger(val logger: Logger)
