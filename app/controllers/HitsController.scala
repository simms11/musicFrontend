package controllers

import javax.inject.Inject
import models.Hit
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext.Implicits.global
import models.Writeables.jsonWritable
import play.api.libs.json.Json
import models.Hit


class HitsController @Inject()(ws: WSClient, cc: ControllerComponents) extends AbstractController(cc) {

  def  index()= Action { implicit request =>
    return Ok(views.html.getHit.render(Hit.form()));
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
}
