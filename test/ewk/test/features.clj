(ns ewk.test.features
  (:require [clojure.contrib.logging :as lg])
  (:require [clojure.contrib.string  :as string])
  (:require [clojure.contrib.java-utils :as java-utils])    
  (:require [ewk.features :as f])
  (:require [net.cgrand.enlive-html :as html])
  (:use clojure.pprint)
  (:use clojure.contrib.json)
  (:use clojure.test))

(deftest title-contains-login-marker
  (is (= true
	 (f/title-contains-login-marker 
	  (html/html-snippet "<html><head><title>Sign in</title></head></html>"))))
  (is (= false
	 (f/title-contains-login-marker 
	  (html/html-snippet "<html><head><title>Other</title></head></html>")))))

(deftest n-password-input
  (is (= 1
	 (f/n-password-input 
	  (html/html-snippet "<html><body><input name='PASSWORD'/></body></html>"))))
  (is (= 0
	 (f/n-password-input 
	  (html/html-snippet "<html><body><input name='other'/></body></html>")))))

(deftest n-input-boxes
  (is (= 1
	 (f/n-input-boxes
	  (html/html-snippet "<html><body><input name='PASSWORD'/></body></html>"))))
  (is (= 2
	 (f/n-input-boxes
	  (html/html-snippet (str "<html><body><input name='LOGIN'/>"
				  "<input name='PASSWORD'/></body></html>"))))))
(deftest n-your-account
  (is (= 1 
	 (f/n-your-account 
	  (html/html-snippet "<html><body><div id='rhf'><div>Your Account</div></div></body></html>")))))

(deftest n-customer-reviews
  (is (= 1 
	 (f/n-customer-reviews
	  (html/html-snippet "<html><body><a name='customerReviews'></div></body></html>")))))