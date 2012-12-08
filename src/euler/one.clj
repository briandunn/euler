#!/usr/bin/env clj

; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
; Find the sum of all the multiples of 3 or 5 below 1000.

(ns euler.one)
(use 'clojure.test)

(defn multiples-of
  [factors upper]
  (distinct (flatten (map #(range % upper %) factors))))

(defn sum-of-multiples-of
  [factors upper]
  (reduce + (multiples-of factors upper)))

(deftest with-max-one
  (is (= '(1) (multiples-of '(1) 2))))

(deftest with-max-ten
  (is (= '(3 5 6 9) (sort (multiples-of '(3 5) 10)))))

(deftest with-max-ten
  (is (= '(3 5 6 9 10 12 15) (sort (multiples-of '(3 5) 16)))))

(deftest sum-with-max-ten
  (is (= 23 (sum-of-multiples-of '(3 5) 10))))

(defn -main []
  (println (sum-of-multiples-of '(3 5) 1000)))
