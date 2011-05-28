(ns ewk.core
  (:require [clojure.contrib.logging :as lg])
  (:require [clojure.contrib.string  :as string])
  (:require [net.cgrand.enlive-html  :as html])
  (:use clojure.pprint)
  (:use clojure.contrib.json)
  (:gen-class))

(defn -main [& args]
  (lg/info (str "Running with args: " (string/join " " args))))
