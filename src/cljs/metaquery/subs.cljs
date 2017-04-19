(ns metaquery.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))


(re-frame/reg-sub
 :sparql
 (fn [db]
   (:sparql db)))

(re-frame/reg-sub
 :selected-cats
 (fn [db]
   (:cat-a db)))

(re-frame/reg-sub
 :selected-cat-a
 (fn [db [_ i]]
   (nth (:cat-a db) i)))

(re-frame/reg-sub
 :cat-as
 (fn [db]
   (:cat-as db)))


(re-frame/reg-sub
 :filtered-cats
 (fn [db [_ i]]
   (nth (:filtered-cats db) i)))


(re-frame/reg-sub
 :cat-bs
 (fn [db]
   (:cat-bs db)))

(re-frame/reg-sub
 :selected-cat-b
 (fn [db [_ i]]
   (nth (:cat-b db) i)))
