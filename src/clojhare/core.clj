(ns clojhare.core
  (require [clojhare.utils       :as util])
  (use     [clojure.data.json    :only (read-json)]))

(defn insert [json]
  (let [j (read-json json)]
    (println
      (map str (keys j)) (map str (vals j)))))

(defn -main [& args]
  (util/consume #(insert %) "appointments_appointment_hours"))