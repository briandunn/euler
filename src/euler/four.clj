; A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91  99.
; Find the largest palindrome made from the product of two 3-digit numbers.

(ns euler.four (:use clojure.test))

(defn largest-n-digit-number
  [n] (Integer. (reduce str (map (fn [_] "9") (range n)))))

(defn largest-product-of-n-digit-numbers
  [n] (#(* % %) (largest-n-digit-number n)))

(defn digits-of
  [n] (rest (clojure.string/split (str 99999) #"")))

(defn palindromic?
  [a]
  (or
    (empty? a)
    (and
      (= (first a) (last a))
      (palindromic? (vec (rest (pop a)))))))


(deftest test-largest-product-of-n-digit-numbers
  (is (= 9 (largest-n-digit-number 1)))
  (is (= 81 (largest-product-of-n-digit-numbers 1)))
  (is (= (* 99 99) (largest-product-of-n-digit-numbers 2))))

(deftest test-products-of-n-digit-numbers
         )

(deftest test-palindromic?
  (is (not (palindromic? [1 0])))
  (is (palindromic? [1 1]))
  (is (palindromic? [1 9 1]))
  (is (palindromic? [1 3 3 1]))
  (is (palindromic? [1 3 9 3 1]))
  (is (palindromic? [0])))
