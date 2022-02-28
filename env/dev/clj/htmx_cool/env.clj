(ns htmx-cool.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [htmx-cool.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[htmx-cool started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[htmx-cool has shut down successfully]=-"))
   :middleware wrap-dev})
