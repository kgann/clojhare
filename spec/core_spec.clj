(ns core-spec
  (:use
    [speclj.core]
    [clojhare.rabbit.helpers :as rabbit]))

(describe "clojhare.rabbit.helpers#msg-num"
  (it "should find and parse the correct message number"
    (should
      (= 14 (rabbit/msg-num "testing message 14")))))