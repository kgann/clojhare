(ns clojhare.rabbit.helpers)

(defn body [msg]
  (String. (:body msg)))

(defn msg-num [msg]
  (read-string
    (re-find #"\d+" msg)))