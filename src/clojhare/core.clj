(ns clojhare.core
  (require [com.mefesto.wabbitmq :as amqp]))

(defn body [msg]
  (String. (:body msg)))

(defn msg-num [msg]
  (read-string
      (re-find #"\d+" (body msg))))

(defn -main [& args]
  (amqp/with-broker {:uri "amqp://localhost"}
    (amqp/with-channel
      (amqp/with-queue "appointments_appointment_hours"
        (doseq [msg (amqp/consuming-seq)]
          (if
            (even? (msg-num msg))
              (amqp/ack (-> msg :envelope :delivery-tag))
              (println (str "Odd number - not consumed") (msg-num msg))))))))