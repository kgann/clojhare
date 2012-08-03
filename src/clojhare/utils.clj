(ns clojhare.utils
  (require [com.mefesto.wabbitmq :as amqp]))

(defn body [msg]
  (String. (:body msg)))

(defn delivery-tag [msg]
  (-> msg :envelope :delivery-tag))

(defn consume
  ; Expects a function and a queue name to be provided
  ; Optional host address
  ; ack? boolean - f must return true
  ; Lazely consumes messages
  ([f queue-name] (consume f queue-name "amqp://localhost"))
  ([f queue-name host] (consume f queue-name host true))
  ([f queue-name host ack?]
    (amqp/with-broker {:uri host}
      (amqp/with-channel
        (amqp/with-queue queue-name
          (doseq [msg (amqp/consuming-seq)]
            (let [result (f (body msg))]
              (if (and result ack?)
                (amqp/ack (delivery-tag msg))))))))))