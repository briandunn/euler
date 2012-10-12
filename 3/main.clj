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

(use '(clojure test set))

(defn primes
  ([m]
    (primes [2] (range 3 m)))
  ([p s]
     (loop [primes p series s]
       (let [prime (last primes)]
         (if (empty? series)
           primes
           (recur (conj primes (first series)) (doall (filter #(not (zero? (mod % prime))) (rest series))))
           )))))

; for each prime:
  ; check if divisible by prime P
  ; if it is add that prime to the factor list and begin to work against the result of the devision instead
(defn factors [n] (filter #(= 0 (mod n %)) (primes n)))

(deftest test-primes
  (is (= '(2) (primes 2)))
  (is (= '(2 3 5 7 11) (primes 12)))
         )

; (deftest test-factors
;   (is (= '(5 7 13 29) (factors 13195))))

(run-tests)
; (println (last (primes 851475143)))
; (println (last (factors 600851475143)))
