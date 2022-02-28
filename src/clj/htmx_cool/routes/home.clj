(ns htmx-cool.routes.home
  (:require
   [htmx-cool.layout :as layout]
   [clojure.java.io :as io]
   [htmx-cool.middleware :as middleware]
   [hiccup.core :refer [html]]
   [hiccup.element :refer [javascript-tag]]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defonce click-count (atom 0))

(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp) :click-count @click-count}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn click-page [request]
  (-> (html
       [:div {:id "parent-div"} (str "Clicked: " @click-count)]
       [:button {:hx-get "/inc"
                 :hx-trigger "click"
                 :hx-target "#parent-div"
                 :hx-swap "innerHTML"}
        "Increment"]
       [:button {:hx-get "/dec"
                 :hx-trigger "click"
                 :hx-target "#parent-div"
                 :hx-swap "innterHTML"}
        "Decrement"]
       [:script {:src "/assets/htmx.org/dist/htmx.js"}])
      (response/ok)
      (response/content-type "text/html")))

(defn click-action [action]
  (fn [request]
    (response/ok (str "Clicked: " (swap! click-count action)))))

(defn home-routes []
  [ "" 
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/click" {:get click-page}]
   ["/inc" {:get (click-action inc)}]
   ["/dec" {:get (click-action dec)}]])
