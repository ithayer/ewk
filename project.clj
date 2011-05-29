(defproject ewk "1.0.0-SNAPSHOT"
  :description "(e)nlive (w)ebpage (k)lassifier"
  :dependencies [[org.clojure/clojure "1.2.1"]
		 [org.clojure/clojure-contrib "1.2.0"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
		 [net.defn/enlive "1.0.0-SNAPSHOT"]
		 [org.clojars.bmabey/clj-ml "0.1.0"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [org.clojars.autre/lein-vimclojure "1.0.0"]
                     [vimclojure/server "2.2.0"]]
  :main ewk.core)
