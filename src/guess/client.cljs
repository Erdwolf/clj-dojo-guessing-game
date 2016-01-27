(ns guess.client
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defonce message (atom "I'm thinking of a number!"))

(def make-a-guess
  #(go (let [number (-> % .-target .-value)]
         (reset! message (str "The number is "
                              (:body (<! (http/post (str "/guess/" number))))
                              " "
                              number
                              "!")))))

(defn guess-form []
  [:section
    [:h1 @message]
    [:p "Enter your guess:"]
    [:input {:on-change make-a-guess}]])

(reagent/render-component [guess-form]
                          (. js/document (getElementById "app")))
