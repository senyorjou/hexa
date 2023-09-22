(ns hexa.canvas)

(def canvas (.getElementById js/document "board"))
(def ctx (.getContext canvas "2d"))

(def A (* 2 (/ js/Math.PI 6)))
(def R 10)
(def COS-A (js/Math.cos A))
(def SIN-A (js/Math.sin A))


(defn clear-board []
  (.clearRect ctx 0 0 canvas.width canvas.height))


(defn draw-hexa
  ([x y]
   (draw-hexa x y "#CCC8AA"))
  ([x y color]
   (.beginPath ctx)
   (doall
    (for [i (range 6)
          :let [X (+ x (* R (js/Math.cos (* i A))))
                Y (+ y (* R (js/Math.sin (* i A))))]]
      (.lineTo ctx X Y)))
   (.closePath ctx)
   (set! (.-fillStyle ctx) color)
   (.fill ctx)
   (set! (.-lineWidth ctx) 0.3)
   (set! (.-strokeStyle ctx) "#777")
   (.stroke ctx)))


(defn draw
  "Converts a x,y point to the hexa map
   x increments by the R radius
   y is doubled and increments also if odd value"
  [x y color]
  (let [X (* (inc x) (* R (+ 1 COS-A)))
        Y (cond-> (* (* 2 (inc y)) R SIN-A)
            (odd? x) (+ (* R SIN-A)))]
    (draw-hexa X Y color)))


(defn create-object [x y color]
  (draw x y color)
  (draw (dec x) y color)
  (draw (dec x) (inc y) color)
  (draw x (dec y) color)
  (draw x (inc y) color)
  (draw (inc x) y color)
  (draw (inc x) (inc y) color))


(defn draw-board
  "Draws a row at vertical position y"
  [width height]
  (doall
   (for [x (range width)
         y (range height)]
     (draw x y "#CCC8AA"))))
