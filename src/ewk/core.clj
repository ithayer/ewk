(ns ewk.core
  (:require [clojure.contrib.logging :as lg])
  (:require [clojure.contrib.string  :as string])
  (:require [net.cgrand.enlive-html  :as html])
  (:require [ewk.features])
  (:use clojure.pprint)
  (:use clojure.contrib.json)
  (:gen-class))

(defn -main [& args]
  (lg/info (str "Running with args: " (string/join " " args))))

(defn add-features-to-dataset
  "Evaluates the feature functions defined in ewk.features for the provided
  html. Returns a map of feature name to result."
  [html]
  (let [features (ns-publics 'ewk.features) html-input (html/html-snippet html)]
    (zipmap
      (map keyword (keys features))
      (map #(% html-input) (vals features)))))
