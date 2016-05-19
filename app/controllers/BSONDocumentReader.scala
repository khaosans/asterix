package controllers

import reactivemongo.bson.BSONDocument

trait BSONDocumentReader[DocumentType] {
  def read(buffer: BSONDocument): DocumentType
}

