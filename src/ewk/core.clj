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
  [html]
  (let [features (ns-publics 'ewk.features)]
    (hash-map :html html
              :features (apply hash-map
                               (interleave
                                 (map keyword (keys features))
                                 (map #(% html) (vals features)))))))
