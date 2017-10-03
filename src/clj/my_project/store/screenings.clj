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

(defn get-by-id
  "Check out make id function for id format"
  [id]
  (let [screening (get @storage id)
        screening (assoc screening :date (t/zoned-date-time (:date screening) 0))]
   screening))

(defn create-screening
  [screening]
  (enduro/swap!
     storage
     (fn [screenings]
       (let [;;TODO id should be made based on film-screening-date-time
             id (:id screening)
             screening (assoc screening :date (format-date (:date screening)))]
         (assoc screenings id screening)))))
