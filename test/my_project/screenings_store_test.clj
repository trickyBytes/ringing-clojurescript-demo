(ns my-project.screenings-store-test
  (:require [clojure.test :refer :all]
            [java-time :as t]
            [my-project.store.screenings :as store]))

(deftest can-store-and-retrieve
  (let [screening {:film-name "The Levelling"
                   :film-rating "PG"
                   :film-length 120
                   :film-country "USA"
                   :film-date (t/local-date)
                   :film-description "Some longer text"
                   :date (t/zoned-date-time (t/local-date-time "yyyy-MM-dd'T'HH:mm" "2020-02-26T19:30") 0)
                   :allow-bookings true
                   :max-seats 32
                   :max-wheelchairs 2
                   :id "the-levelling-2017-02-26-19-30"}
        screening (store/create-screening screening)
        screening (store/get-by-id "the-levelling-2017-02-26-19-30")]
    (testing "Screening created"
      (is (not(nil? screening)))
      (is (= (type (:date screening)) java.time.ZonedDateTime)))))
