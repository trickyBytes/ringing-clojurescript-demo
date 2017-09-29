(ns my-project.handler
  (:import java.net.URI)
  (:require  [compojure.core :refer :all]
             [compojure.core :as compojure :refer (GET POST ANY defroutes)]
             [compojure.route :as route]
             [ring.util.response :as response]
             [ring.middleware.webjars :refer [wrap-webjars]]
             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]

             [my-project.view.screenings :as screenings]
             [my-project.store.screenings :as store]

             [clj-time.format :as f]
             [java-time :as t]))

(defn format-date-time
  "String date in format yyyy-dd-MM'T'HH:mm"
  [str-date]
  (f/parse (f/formatter "yyyy-MM-dd'T'HH:mm") str-date))

(defroutes admin-routes
  (GET "/screening/add" [] (screenings/add))
  (POST "/screening/add" {params :params}
        (let [screening {:film-name (:name params)
                         :film-rating (:rating params)
                         :film-length (:length params)
                         :film-country (:country params)
                         :film-date (:release-date params)
                         :film-description (:description params)
                         :date (t/zoned-date-time (format-date-time (:date params)))
                         :allow-bookings (:allowbookings params)
                         :max-seats (:max-seats params)
                         :max-wheelchairs (:wheel-chairs params)
                         :id "slug"}]
          (println (t/format "MM/dd" (:date screening)))
          ;(store/create-screening screening)
          (screenings/screening-list))))

(defroutes screening-routes
  (GET "/" [] (screenings/screening-list)))

(defroutes app-routes
  admin-routes
  screening-routes)

(def app
  (->
   app-routes
   wrap-webjars
   (wrap-defaults site-defaults)))
