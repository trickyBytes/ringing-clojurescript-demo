(ns my-project.store.screenings
  (:require [alandipert.enduro :as enduro]
            [my-project.store.env :as env]
            [java-time :as t]))
   
(def storage
  ;; a map from user-id to user details, each being a map of attributes
  (enduro/file-atom
   {}
   (str env/storage-directory "screenings.edn")))

(defn- format-date
  [expiry]
  (if-let [expiry expiry]
    (t/to-java-date expiry)
    expiry))

(defn create-screening
  [screening]
  (enduro/swap!
     storage
     (fn [screenings]
       (let [slug (:slug screening)]
         (assoc screenings slug screening)))))
