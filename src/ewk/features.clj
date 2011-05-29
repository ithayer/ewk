(ns ewk.features
  (:use [net.cgrand.enlive-html]))

(defn is-home
  "Check if the currentpage paragraph's content is Home"
  [html]
  ((complement empty?)
     (filter #(= "Home" (apply str (% :content)))
             (select html [:p#currentpage]))))

(defn header-exists
  "Check if there is a div with id header"
  [html]
  ((complement empty?)
     (select html [:div.header])))
