; 5

; 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
; What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
;
(ns euler.five
  (:use clojure.test)
  (:require [clojure.math.numeric-tower :as math]))

(defn primes
  ([]
    (primes 2 (next (next (next (range))))))
  ([p s]
   (cons p (lazy-seq (primes (first s) (filter #( not= 0 (mod % p)) (next s)))))))

(defn factor? [n of] (= 0 (mod of n)))

(defn factors
  ([n] (factors n (primes)))
  ([n prime-seq]
   (let [this-prime (first prime-seq) rest-of-primes (next prime-seq)]
     (if (factor? this-prime n)
       (cons this-prime (factors (/ n this-prime) prime-seq))
       (if (< this-prime n)
         (factors n rest-of-primes)
         '())))))

(defn least-common-multiple
  [l]
  (reduce *
    (map
      #(math/expt (key %) (val %))
      (apply merge-with max
        (map frequencies (map factors l))))))

(deftest test-least-common-multiple
  (is (= 2520 (least-common-multiple (range 1 11)))))

(defn -main []
  (println (least-common-multiple (range 1 21))))
