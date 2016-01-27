(ns guess.server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [resource-response content-type]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def secret (atom (inc (rand-int 30))))

(defn guess [number] (cond (= @guess number) "correct"
                           (< @guess number) "lower"
                           (> @guess number) "higher"))

(defroutes app-routes
  (GET "/" [] (content-type (resource-response "index.html" {:root "public"})
                            "text/html"))
  (POST "/guess/:number" [number] (guess (Integer/parseInt number)))
  (route/not-found "Not Found"))

(def handler
  (wrap-defaults app-routes api-defaults))
