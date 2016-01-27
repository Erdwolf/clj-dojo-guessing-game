(defproject guess "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-defaults "0.1.5"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/core.async "0.2.374" :exclusions [org.clojure/tools.reader]]
                 [reagent "0.6.0-alpha"]
                 [cljs-http "0.1.39"]]

  :plugins [[lein-figwheel "0.5.0-5"]
            [lein-cljsbuild "1.1.2" :exclusions [[org.clojure/clojure]]]]

  :cljsbuild {:builds
              [{:source-paths ["src"]
                :figwheel true
                :compiler {:main guess.client
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/guess.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}]}

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler guess.server/handler
             })
