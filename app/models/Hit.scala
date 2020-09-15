package models
import play.api.data.Forms._


import play.api.data.Form
import play.api.libs.json.Json

case class Hit(id:Int, name:String, yearReleased:Int, album:String, chartedUSA:Int, fact:String)

object Hit {
  implicit val format = Json.format[Hit]

  val form = Form(
    mapping(
      "id" -> number,
      "name" -> text,
      "yearReleased" -> number,
      "album" -> text,
      "chartedUSA" -> number,
      "fact" -> text
    )(Hit.apply)(Hit.unapply)
  )

}
