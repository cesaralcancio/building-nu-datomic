(ns building-nu-datomic.example-2-simple-insert
  (:require [building-nu-datomic.datomic-config.config :as config]
            [datomic.client.api :as d])
  (:import (java.util Date)))

;; create schema
(config/delete-database)
(config/create-db-with-schema)

;; get the connection
(def conn (config/conn))

(def random-order
  {:order/id         1
   :order/value      123.99M
   :order/status     :pending
   :order/created-at (new Date)})
(println random-order)

(d/transact conn {:tx-data [random-order]})

;; find all
(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]] (d/db conn))

;; find by id
(def id (:order/id random-order))
(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :in $ ?id
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]]
     (d/db conn) id)
