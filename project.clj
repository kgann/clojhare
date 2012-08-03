(defproject clojhare "0.0.1"
  :description "Consume RabbitMQ messages"
  :url "http://github.com/kgann/clojhare"
  :dependencies [
    [org.clojure/clojure "1.4.0"]
    [org.clojure/data.json "0.1.2"]
    [com.mefesto/wabbitmq "0.2.2"]
    [korma "0.3.0-beta7"]
    [speclj "2.1.2"]]
  :plugins [
    [lein-ring "0.4.5"]
    [speclj "2.1.2"]]
  :test-paths ["spec/"]
  :main clojhare.core)