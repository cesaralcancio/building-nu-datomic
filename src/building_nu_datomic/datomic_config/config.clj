(ns building-nu-datomic.datomic-config.config
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :dev-local
                       :system      "dev"
                       :storage-dir "/Users/cesar.alcancio/pessoal/datomic/storage"}))

(def db-orders {:db-name "orders"})

(def order-schema
  [{:db/ident       :order/id
    :db/unique      :db.unique/identity
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/doc         "The unique identifier of the order"}
   {:db/ident       :order/created-at
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one
    :db/doc         "The date and time order was created"}
   {:db/ident       :order/value
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "The value of the order, this is the amount the customer needs to pay"}
   {:db/ident       :order/status
    :db/valueType   :db.type/keyword
    :db/cardinality :db.cardinality/one
    :db/doc         "The status of the order"}])

(defn conn []
  (d/connect client db-orders))

(defn create-db-with-schema []
  (d/create-database client db-orders)
  (d/transact (conn) {:tx-data order-schema}))

(defn delete-database []
  (d/delete-database client db-orders))

(defn list-databases []
  (d/list-databases client {}))
