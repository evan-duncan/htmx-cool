(ns htmx-cool.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[htmx-cool started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[htmx-cool has shut down successfully]=-"))
   :middleware identity})
