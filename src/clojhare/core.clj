(ns clojhare.core
  (require [clojhare.utils    :as util])
  (use     [clojure.data.json :only (read-json)]
           korma.db korma.core))

(defdb dev
  (mysql {:db "clojhare" :user "root" :password "temp!@#$"}))

(defentity appt
  (database dev))

(defn insert-via-json [json]
  (insert appt
    (values (read-json json))))

(defn -main [& args]
  (util/consume-with insert-via-json "appointments_appointment_hours"))