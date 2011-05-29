(ns ewk.dataset
  (:require [clojure.contrib.logging :as lg])
  (:require [clojure.contrib.string  :as string])
  (:require [clojure.contrib.java-utils :as java-utils])    
  (:use clojure.pprint)
  (:use clojure.contrib.json))

;; Dataset manipulation.
;; 
;; Dataset formats are like
(def example-dataset 
     [ {:file "index.html" :class "index" :features { :is_home 1.0 :a 1.0 } } ])

(defn- read-single [filename meta featureset-fn]
  "Reads a single file into a single document in a dataset."
  (if (-> filename (java-utils/file) .exists)
    (merge meta {:features (featureset-fn (slurp filename))})
    (lg/error (str "File " filename " doesn't exist."))))

(defn- join-path [base-dir path]
  "Simply joins paths."
  (str base-dir "/" path))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public interface.

(defn read [base-dir files-spec featureset-fn]
  "Reads a specification of a set of files and classes to a dataset map.
   eg: (read '/home/user/data' [ { :file 'index.html' :class } ] myfn)
   myfn maps html -> a map of features. The result of that gets added to the :features key."
  ;; For each file in the filespec, prepend the basedir, read in 
  ;; and apply the featureset-fn to the data.
  (remove nil?
	  (map #(read-single (join-path base-dir (:file %)) % featureset-fn) files-spec)))
  
  

