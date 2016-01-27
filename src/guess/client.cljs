(ns guess.client
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce message (atom "I'm thinking of a number!"))

(def make-a-guess
  #(reset! message (str "The number is not "
                        (-> % .-target .-value)
                        "!")))

(defn guess-form []
  [:section
    [:h1 @message]
    [:p "Enter your guess:"]
    [:input {:on-change make-a-guess}]])

(reagent/render-component [guess-form]
                          (. js/document (getElementById "app")))
