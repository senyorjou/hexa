(ns hexa.main)

(defn main

  []
  [:div {:class "world"}
   (for [y (range 20)]
     [:div {:class "ibws-fix"}
      (for [x (range 30)]
        [:div {:class "hexa"}
         [:div {:class "hexatext"} (str x "," y)]])])])
