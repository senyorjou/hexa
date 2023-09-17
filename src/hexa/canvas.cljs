(ns hexa.canvas)

(def canvas (.getElementById js/document "board"))
(def ctx (.getContext canvas "2d"))

(def A (* 2 (/ js/Math.PI 6)))
(def R 10)
(def COS-A (js/Math.cos A))
(def SIN-A (js/Math.sin A))


(defn x-deltas
  "Returns max x-deltas, begins at second element of the sequence"
  [max]
  (map
   (fn [x] (* x (* R (+ 1 (js/Math.cos A)))))
   (range 1 (inc max))))

(defn y-sequence
  [step]
  (* step R (js/Math.sin A)))

(defn y-deltas
  [initial-y]
  (cycle [initial-y (+ initial-y (* R (js/Math.sin A)))]))


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


(defn to-hexa
  "Converts a x,y point to the hexa map"
  [x y color]
  (let [X (* (inc x) (* R (+ 1 COS-A)))
        Y (cond-> (* (* 2 (inc y)) R SIN-A)
            (odd? x) (+ (* R SIN-A)))]
    (draw-hexa X Y color)))

(defn draw-row
  "Draws a row at vertical position y"
  [width y]
  (mapv (fn [x-delta y-delta]
          (draw-hexa x-delta (+ y y-delta)))
        (x-deltas width)
        (y-deltas y)))



(defn draw-board
  "Draws a row at vertical position y"
  [width height]
  (doall
   (for [x (range width)
         y (range height)]
     (to-hexa x y "#CCC8AA"))))
