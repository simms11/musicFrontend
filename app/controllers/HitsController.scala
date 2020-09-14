package controllers

import javax.inject.Inject
import models.Hit
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext.Implicits.global

class HitsController @Inject()(ws: WSClient, cc: ControllerComponents) extends AbstractController(cc) {

  def getHits() = Action.async {
    import models.Writeables.jsonWritable

    ws.url("http://localhost:9000/hits/3").get().map { response =>
      Ok(response.json.as[Hit])
    }
  }
}
