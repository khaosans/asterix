package controllers

import reactivemongo.api.SerializationPack
import reactivemongo.api.collections.GenericQueryBuilder

trait GenericCollection {
  val pack: SerializationPack

  def find[S](selector: S)(implicit swriter: pack.Writer[S]): GenericQueryBuilder[pack.type]
}