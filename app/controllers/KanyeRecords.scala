package controllers

import javax.inject._
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class KanyeRecords @Inject() (controllerComponents: ControllerComponents) extends AbstractController(controllerComponents){

  def getRecord = Action {
    Ok(views.html.records())
  }

  def getHitsList (name:String,yearReleased:Int, album:String, chartedUSA:Int,fact:String )= Action{
    Ok(s"$name was released in the year $yearReleased. $name comes from the album: $album. It's highest US charted position is $chartedUSA." +
      s"Song Fact: $fact")
  }


}
