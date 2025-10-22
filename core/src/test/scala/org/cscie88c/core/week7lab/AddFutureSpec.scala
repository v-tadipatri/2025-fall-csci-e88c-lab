package org.cscie88c.core.week7lab

import org.scalatest.flatspec.AsyncFlatSpec

import scala.concurrent.Future

/**
 * Based off https://www.scalatest.org/user_guide/async_testing
 */
class AddSpec extends AsyncFlatSpec {

  def addSoon(addends: Int*): Future[Int] = Future { Thread.sleep(5000); addends.sum }

  behavior of "addSoon"

  it should "eventually compute a sum of passed Ints" in {
    val futureSum: Future[Int] = addSoon(1, 2)
    // You can map assertions onto a Future, then return
    // the resulting Future[Assertion] to ScalaTest:
    futureSum map { sum => {
        println(s"got eventual sum: ${sum}")
        assert(sum == 3)
      }
    }
  }

  def addNow(addends: Int*): Int = {
    Thread.sleep(5000)
    addends.sum
  }

  "addNow" should "immediately compute a sum of passed Ints" in {
    val sum: Int = addNow(1, 2)
    println(s"Got immediate sum = ${sum}")
    // You can also write synchronous tests. The body
    // must have result type Assertion:
    assert(sum == 3)
  }
}
