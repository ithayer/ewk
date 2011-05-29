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

(defn title-contains-login-marker [html]
  "Returns true if the title contains text that normally signifies logging in."
  (not (nil? 
	(re-find #"(?i:sign in|login)" 
		 (apply str (:content (first (select html [:title]))))))))

(defn- contains-password-text [s]
  (when s 
    (re-find #"(?i:password)" s)))

(defn n-password-input [html]
  "Returns true if there's an input that asks for 'password'."
  (count (select html [(pred #(contains-password-text (-> % :attrs :name)))])))

(defn n-buying-divs [html]
  "Returns count of divs that have a 'buying class'."
  (count (select html [:.buying])))

(defn n-input-boxes [html]
  "Returns the number of 'input' boxes."
  (count (select html [:input])))

(defn len-nav-greeting [html]
  "Returns the length of the navGreeting div."
  (count (apply str (:content (first (select html [:.navGreeting]))))))

(defn len-nav-message [html]
  "Returns the length of the navMessage div."
  (count (apply str (:content (first (select html [:.navMessage]))))))

(defn quantity-dropdown-div-exists [html]
  "Returns nubmer of quantity dropdown boxes."
  (count (select html [:#quantityDropdownDiv])))

(defn n-your-account [html]
  "Returns true if has a heading that contains your account in an #rhf div."
  (count (map #(re-find #"(?i:your account)" (apply str (:content %))) 
	      (select html [:#rhf]))))

(defn n-customer-reviews [html]
  "Returns the number of divs with the name 'customerReviews'."
  (count (select html [(attr= :name "customerReviews")])))