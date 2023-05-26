(ns building-nu-datomic.example-3-update-and-history
  (:require [building-nu-datomic.datomic-config.config :as config]
            [datomic.client.api :as d])
  (:import (java.util UUID Date)))

;; create schema
(config/delete-database)
(config/create-db-with-schema)

;; get the connection
(def conn (config/conn))

(def order-id (UUID/randomUUID))
(def order-date (new Date))
(def random-order
  {:order/id         order-id
   :order/value      123.99M
   :order/status     :pending
   :order/created-at order-date})
(println random-order)

(def create-response (d/transact conn {:tx-data [random-order]}))
(clojure.pprint/pprint (clojure.pprint/pprint create-response))

;; find all
(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]] (d/db conn))

;; update
(def updated-random-order (assoc random-order :order/status :paid))
(println updated-random-order)
(def update-response (d/transact conn {:tx-data [updated-random-order]}))
(clojure.pprint/pprint update-response)

;; find all
(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]] (d/db conn))

;; find before in time
;; get transaction date
(def create-tx-date (.tx (first (:tx-data create-response))))
(def update-tx-date (.tx (first (:tx-data update-response))))

(d/q '[:find ?id ?value ?status ?created-at
       :keys id value status created-at
       :where
       [?e :order/id ?id]
       [?e :order/value ?value]
       [?e :order/status ?status]
       [?e :order/created-at ?created-at]] (d/as-of (d/db conn) update-tx-date))

;; check history
;; reference: https://docs.datomic.com/pro/tutorial/history.html
(d/q '[:find ?tx ?instant ?op ?status
       :keys tx instant op status
       :in $ ?id
       :where
       [?e :order/id ?id]
       [?e :order/status ?status ?tx ?op]
       [?tx :db/txInstant ?instant]]
     (d/history (d/db conn)) order-id)
