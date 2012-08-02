(defproject clojhare "0.0.1"
  :description "Consume rabbitmq messages"
  :url "http://github.com/kgann/clojhare"
  :dependencies [[org.clojure/clojure "1.3.0"] [korma "0.3.0-beta7"] [com.mefesto/wabbitmq "0.2.2"]]
  :plugins [[lein-ring "0.4.5"]]
  :main clojhare.core)