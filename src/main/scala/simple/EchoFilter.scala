package simple

import javax.servlet.http.HttpServletRequest
import unfiltered.request.{Path => UFPath}
import unfiltered.request._
import unfiltered.response._
import com.google.appengine.api.datastore.KeyFactory

class EchoFilter extends unfiltered.filter.Planify(PartialFunction(EchoFilter.function)) {
}

object EchoFilter {
  val function: PartialFunction[HttpRequest[HttpServletRequest], ResponseString] = {
    case GET(UFPath(Seg(what :: Nil)) & Params(params0)) =>
      val key = KeyFactory.createKey(classOf[Counter].getSimpleName, what)
      val counter = CounterAdapter.get(key) getOrElse new Counter(key, 0)
      val inc = new Counter(key, counter.count + 1)
      CounterAdapter.save(inc)
      ResponseString(what + inc.count.toString + "!")
  }
}
