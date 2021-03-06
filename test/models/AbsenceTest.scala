package models

import org.specs2.mutable._
import org.specs2.runner
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc.AnyContentAsJson
import play.api.libs.json._
import play.api._
import play.api.mvc._

class AbsenceTest extends Specification {
  "make sure serialization and deserialisation does not break object" in {
    val absence = Absence(1234, 432, "Parental leave", 12345, 123456)
    val jsonString = Json.toJson(absence).toString()
    println(jsonString)
    val absenceII = Json.parse(jsonString).as[Absence]
    absence must equalTo(absenceII)
  }
  
  "make sure serialization works if optional id is missing" in {
    val jsonString = ("""{"userId":4321,"description":"Parental leave","start":12345,"end":123456}""")
    val absence = Json.parse(jsonString).as[Absence]
    absence.id must equalTo(-1)
  }
}