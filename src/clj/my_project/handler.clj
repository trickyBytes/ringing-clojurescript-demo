(ns my-project.handler
  (:import java.net.URI)
  (:require  [compojure.core :refer :all]
             [compojure.core :as compojure :refer (GET POST ANY defroutes)]
             [compojure.route :as route]
             [ring.util.response :as response]
             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" []
       (response/redirect "/help.html"))
  (GET "/app" []
       (str "<!DOCTYPE html>"
        "<html>"
        "<head>"
        "<link rel=\"stylesheet\" href=\"css/page.css\" />"
        "</head>"
        "<body>"
        "<div>"
        "<p id=\"clickable\">Click me!</p>"
        "</div>"
        "<script src=\"js/cljs.js\"></script>"
        "</body>"
        "</html>")))

(def app
  (->
   app-routes
   (wrap-defaults site-defaults)))
