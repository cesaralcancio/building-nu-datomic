(ns building-nu-datomic.example-1-schema
  (:require [building-nu-datomic.datomic-config.config :as config]))

;; Datomic Datom
;; A datom is an immutable atomic fact that represents the addition or retraction of a relation between an entity (expressed as five-tuple).
;; An entity is a set of datoms that are all about the same Entity number.
;; https://docs.datomic.com/cloud/whatis/data-model.html
;;
;; [Entity  Attribute         Value       Transaction   Operation]
;; [42      :order/id         1           1234          true]
;; [42      :order/created-at 2023-05-28  1234          true]
;; [42      :order/value      123.99M     1234          true]
;; [42      :order/status     :pending    1234          true]
;; [43      :order/id         2           4567          true]
;; [43      :order/created-at 2023-05-28  4567          true]
;; [43      :order/value      123.99M     4567          true]
;; [43      :order/status     :pending    4567          true]
;; ...
;; [Entity    Attribute         Value                 Transaction   Operation]
;; [1234      :db/txInstant     2023-05-28 01:05:00   1234          true]
;; [4567      :db/txInstant     2023-05-28 01:10:00   4567          true]

;; Let's define the entities!
(config/create-db-with-schema)
