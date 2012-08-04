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
   - ack? boolean - func must return true to ack
   Consumes messages and executes func(msg)"
  ([func queue-name & {:keys [host ack?] :or {host "amqp://localhost" ack? true}}]
    (amqp/with-broker {:uri host}
      (amqp/with-channel
        (amqp/with-queue queue-name
          (doseq [msg (amqp/consuming-seq)]
            (let [res (func (body msg))]
              (if (and res ack?)
                (ack msg)))))))))