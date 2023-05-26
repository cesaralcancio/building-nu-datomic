(ns building-nu-datomic.core
  (:require [building-nu-datomic.datomic-config.config :as config]
            [datomic.client.api :as d])
  (:import (java.util UUID Date)))

(config/create-db-with-schema)
(def conn (config/conn))
(def random-order
  {:order/id         (UUID/randomUUID)
   :order/value      123.99M
   :order/status     :pending-payment
   :order/created-at (new Date)})
(println random-order)

(d/transact conn {:tx-data [random-order]})

(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]] (d/db conn))

(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :in $ ?id
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]]
     (d/db conn) (:order/id random-order))
