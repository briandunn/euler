#!/usr/bin/env clj

; Each new term in the Fibonacci sequence is generated by adding the previous
; two terms. By starting with 1 and 2, the first 10 terms will be:
; 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
; By considering the terms in the Fibonacci sequence whose values do not exceed
; four million, find the sum of the even-valued terms.

(ns euler.two)
(use 'clojure.test)

(defn fibonacci
  ([a b]
    (cons a (lazy-seq (fibonacci b (+ b a)))))
  ([]
    (fibonacci 1 1)))

(defn even [s] (filter even? (s)))

(defn less-than [n s] (take-while #(< % n) s))

(deftest first-term
  (is (= '(1) (take 1 (fibonacci)))))

(deftest first-ten-terms
  (is (= '(1 1 2 3 5 8 13 21 34 55) (take 10 (fibonacci)))))

(deftest first-tree-even
  (is (= '(2 8 34) (take 3 (even fibonacci)))))

(deftest less-than-fourty
  (is (= '(2 8 34) (less-than 40 (even fibonacci)))))

(defn -main []
  (println (->> fibonacci even (less-than 4000000) (reduce +))))
