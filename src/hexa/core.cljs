(ns hexa.core
  (:require
   [hexa.views :as V]
   [reagent.dom :as D]))

;; -------------------------
;; Initialize app

(defn mount-root []
  (D/render [V/home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
