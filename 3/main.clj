#!/usr/bin/env clj
; the prime factors of 13195 are 5, 7, 13 and 29.
; what is the largest prime factor of the number 600851475143 ?
;
;
;Sieve of Eratosthenes
; 1) Create a list of consecutive integers from 2 to n: (2, 3, 4, ..., n).
; 2) Initially, let p equal 2, the first prime number.
; 3) Starting from p, count up in increments of p and mark each of these numbers greater than p itself in the list. These numbers will be 2p, 3p, 4p, etc.; note that some of them may have already been marked.
; 4) Find the first number greater than p in the list that is not marked.
;      If there was no such number, stop.
;      Otherwise, let p now equal this number (which is the next prime), and repeat from step 3.

(use 'clojure.test)

(defn factors [n] ())

(deftest test-factors
  (is (= '(5 7 13 29) (factors 13195))))

(run-tests)
