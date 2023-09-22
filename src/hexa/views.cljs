(ns hexa.views
  (:require [hexa.canvas :as C]
            [hexa.state :as S]
            [hexa.main :as M]))


(defn draw-canvas []
  (C/draw-board 32 24))

(defn clear-canvas []
  (C/clear-board))

(defn change-sq [xy]
  (js/console.log xy))

(defn change []
  (C/draw 4 5 "red")
  (C/draw 3 5 "red")
  (C/draw 2 6 "red")
  (C/draw 1 6 "red")
  )

(defn object []
  (C/create-object 1 1 "red"))




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
              :on-click object}
     "Change!"]
    ]
   (M/main)])
