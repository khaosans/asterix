package controllers

import reactivemongo.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.iteratee.Iteratee
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.BSONDocument

object MongoDb {

  def main(args: Array[String]) {


    // gets an instance of the driver
    // (creates an actor system)
    val driver = new MongoDriver
    val connection = driver.connection(List("localhost"))

    // Gets a reference to the database "plugin"
    val db = connection.database("plugin")

    // Gets a reference to the collection "acoll"
    // By default, you get a Future[BSONCollection].
    val collection = db.map(_ ("acoll"))

    println(collection)

  }

  def listDocs(collection: BSONCollection) = {
    // Select only the documents which field 'firstName' equals 'Jack'
    val query = BSONDocument("firstName" -> "Jack")
    // select only the fields 'lastName' and '_id'
    val filter = BSONDocument(
      "lastName" -> 1,
      "_id" -> 1)

    /* Let's run this query then enumerate the response and print a readable
     * representation of each document in the response */
    collection.
      find(query, filter).
      cursor[BSONDocument].
      enumerate().apply(Iteratee.foreach { doc =>
      println(s"found document: ${BSONDocument pretty doc}")
    })

    // Or, the same with getting a list
    val futureList: Future[List[BSONDocument]] =
      collection.
        find(query, filter).
        cursor[BSONDocument].
        collect[List]()

    futureList.map { list =>
      list.foreach { doc =>
        println(s"found document: ${BSONDocument pretty doc}")
      }
    }
  }

  def print(collection: BSONCollection) = {
    val query = BSONDocument("foo" -> "bar")
    val cursor = collection.find(query).cursor[BSONDocument]
    val futureList: Future[List[BSONDocument]] = cursor.collect[List]()

    futureList.map { list =>
      println("ok, got the list: " + list)
    }
  }

  def streaming(cursor: Cursor[BSONDocument]) =
    cursor.enumerate().apply(Iteratee.foreach { doc =>
      println(s"found document: ${BSONDocument pretty doc}")
    })
}
