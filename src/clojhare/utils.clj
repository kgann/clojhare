(ns clojhare.utils
  (require [com.mefesto.wabbitmq :as amqp]))

(defn body [msg]
  (String. (:body msg)))

(defn delivery-tag [msg]
  (-> msg :envelope :delivery-tag))

(defn ack [msg]
  (amqp/ack (delivery-tag msg)))

(defn consume-with
  "Expects a function and a queue name to be provided
   - Optional host address -  defaults to local rabbit
   - ack? boolean - f must return true to ack
   Lazely consumes messages and executes f(msg)"
  ([f queue-name] (consume-with f queue-name "amqp://localhost"))
  ([f queue-name host] (consume-with f queue-name host true))
  ([f queue-name host ack?]
    (amqp/with-broker {:uri host}
      (amqp/with-channel
        (amqp/with-queue queue-name
          (doseq [msg (amqp/consuming-seq)]
            (let [result (f (body msg))]
              (if (and result ack?)
                (ack msg)))))))))