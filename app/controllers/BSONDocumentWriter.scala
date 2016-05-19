package controllers

import reactivemongo.bson.BSONDocument

trait BSONDocumentWriter[DocumentType] {
  def write(document: DocumentType): BSONDocument
}
