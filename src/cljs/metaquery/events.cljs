(ns metaquery.events
    (:require [re-frame.core :as re-frame]
              [re-com.dropdown :refer [filter-choices-by-keyword]]
              [ajax.core :refer [GET POST]]
              [metaquery.db :as db]))

(def host "http://mist.cs.bath.ac.uk")

(def query-template "PREFIX csv:<http://www.ntnu.no/ub/data/csv#>
PREFIX ssn:<http://purl.oclc.org/NET/ssnx/ssn#>

SELECT ?f ?i 
WHERE {?f csv:hasColumn ?c .
                ?c csv:mapsTo ssn:hasValue .
                ?c csv:hasIndex ?i .}")

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :update-sparql
 (fn [db [_ query]]
   (assoc db :sparql query)))

(re-frame/reg-event-db
 :add-triple
 (fn [db _]
   (assoc (assoc (assoc db :cat-a (conj (:cat-a db) nil)) :cat-b (conj (:cat-b db) nil)) :filtered-cats (conj (:filtered-cats db) []))))

(re-frame/reg-event-db
 :change-cat-a
 (fn [db [_ i new]]
   (println new)
   (assoc-in (assoc-in (assoc-in db [:cat-a i] new) [:filtered-cats i] (vec (filter-choices-by-keyword (:cat-bs db) :cat-id new))) [:cat-b i] nil)))

(re-frame/reg-event-db
 :change-cat-b
 (fn [db [_ i new]]
   (assoc-in db [:cat-b i] new)))

(re-frame/reg-event-db
 :query-response-handler
 (fn [db [_ response]]
   (println response)
   (assoc db :response response)))

(re-frame/reg-event-db
 :error-handler
 (fn [db [_ response]]
   (do
     (println (str "SERVER ERROR: " response))
     db)
   ))

(re-frame/reg-event-db
 :send-sparql
 (fn [db _]
   ;; (js/alert (str "Query is: " (:sparql db)))
   (POST (str host "/query/")
         {:params {:sparql (:sparql db)}
          :format :json
          :handler #(re-frame/dispatch [:query-response-handler %1])
          :error-handler #(re-frame/dispatch [:error-handler %1])})

   db))
