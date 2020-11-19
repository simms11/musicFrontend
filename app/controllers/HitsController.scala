package controllers

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import javax.inject.Inject
import models.Hit
import models.Writeables.jsonWritable
import play.api.i18n.{I18nSupport, MessagesProvider}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}


case class HitsController @Inject()(ws: WSClient, cc: ControllerComponents, ms : MessagesProvider) extends AbstractController(cc) with I18nSupport {
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SingleRequest")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  def  index()= Action.async { implicit request =>
    ws.url("http://localhost:9000/hits/3").get().map { response =>
      val hit = response.json.as[Hit]
      Ok(views.html.getHit(Hit.form.fill(hit))(request,ms))
    }
  }
  def getHit() = Action.async {
    ws.url("http://localhost:9000/hits/3").get().map { response =>
      Ok(response.json.as[Hit])
    }
  }

  def addHit = Action.async { request =>
    val hit = request.body.asInstanceOf[Hit]
    ws.url("http://localhost:9000/hits").post(Json.toJson(hit)).map { response =>
      Ok(response.json.as[Hit])
    }
}

  def addHit(newHit: Hit): Any = Action(parse.json) { implicit request =>
    val responseFuture: Future[HttpResponse] =
      Http(system).singleRequest(
        HttpRequest(
          uri = "http://localhost:9000/hits",
          method = "POST",
          entity = ("application/json", newHit),
          protocol = "HTTP/1.0"
        ))
    responseFuture
      .onComplete {
        case Success(res) => Ok(res)
        case Failure(_) => sys.error("something wrong")
      }
  }
}
