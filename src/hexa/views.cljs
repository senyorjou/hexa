(ns hexa.views
  (:require [hexa.canvas :as C]
            [hexa.state :as S]))


(defn draw-canvas []
  (C/draw-board 32 24))

(defn clear-canvas []
  (C/clear-board))

(defn change-sq [xy]
  (js/console.log xy))

(defn change []
  (C/to-hexa 5 5 "red")
  (C/to-hexa 6 5 "blue")
  (C/to-hexa 7 5 "red")
  )




;; -------------------------
;; Views


(defn home-page []
  [:div
   [:h1 "Welcome to Hexa"]
   [:div {:id "cpanel"}
    [:button {:id "start-button"
              :on-click draw-canvas}
     "Generate!"]
    [:button {:id "clear-canvas"
              :on-click clear-canvas}
     "Clear!"]
    [:input {:type "text"
             :value @S/coords
             :on-change #(reset! S/coords (-> %
                                              .-target
                                              .-value))}]
    [:button {:id "change-sq"
              :on-click change}
     "Change!"]
    ]])
