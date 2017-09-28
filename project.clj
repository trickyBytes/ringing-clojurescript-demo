(defproject my-project "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-webjars "0.1.1"]
                 [ring/ring-core "1.6.2"]
                 [ring/ring-jetty-adapter "1.6.2"]]
  
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ring "0.8.10"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :cljsbuild { 
    :builds {
      :main {
        :source-paths ["src/cljs"]
        :compiler {:output-to "resources/public/js/cljs.js"
                   :optimizations :simple
                   :pretty-print true}
        :jar true}}}
  :main my-project.server
  :ring {:handler my-project.server/app})

