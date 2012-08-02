(ns clojhare.core
  (require
    [com.mefesto.wabbitmq :as amqp]
    [clojhare.rabbit.helpers :as rabbit]))

(defn -main [& args]
  (amqp/with-broker {:uri "amqp://localhost"}
    (amqp/with-channel
      (amqp/with-queue "appointments_appointment_hours"
        (doseq [msg (amqp/consuming-seq)]
          (if
            (even? (rabbit/msg-num (rabbit/body msg)))
              (amqp/ack (-> msg :envelope :delivery-tag))
              (println (str "Odd number - not consumed") (rabbit/msg-num (rabbit/body msg)))))))))