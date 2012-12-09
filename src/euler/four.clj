; A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91  99.
; Find the largest palindrome made from the product of two 3-digit numbers.

(ns euler.four (:use clojure.test))

(defn digits-to-number
  [v] (Integer. (clojure.string/join v)))

(defn largest-n-digit-number
  [n] (digits-to-number (map (fn [_] "9") (range n))))

(defn smallest-n-digit-number
  [n] (digits-to-number (cons "1" (map (fn [_] "0") (range (- n 1))))))

(defn largest-product-of-n-digit-numbers
  [n] (#(* % %) (largest-n-digit-number n)))

(defn all-n-digit-numbers
  [n]
  (range (smallest-n-digit-number n) (+ 1 (largest-n-digit-number n))))

(defn products-of-n-digit-numbers
  [n]
  (flatten
    (let [v (all-n-digit-numbers n)]
      (map (fn [j] (map (fn [i] (* i j)) v)) v))))

(defn digits-of
  [n] (map #(Integer. %) (rest (clojure.string/split (str n) #""))))

(defn palindromic?
  [v]
  (or
    (empty? v)
    (and
      (= (first v) (last v))
      (palindromic? (vec (rest (pop v)))))))

(defn palindromic-products-of-n-digit-numbers
  [n]
  (map digits-to-number
      (filter palindromic?
        (map
          #(vec (digits-of %))
          (products-of-n-digit-numbers n)))))

(deftest test-largest-n-digit-number
  (is (= 9 (largest-n-digit-number 1)))
  (is (= 999 (largest-n-digit-number 3)))
  )

(deftest test-smallest-n-digit-number
  (is (= 1 (smallest-n-digit-number 1)))
  (is (= 100 (smallest-n-digit-number 3)))
  )

(deftest test-products-of-n-digit-numbers
  (is (= '(1 2 3 4 5 6 7 8 9) (take 9 (products-of-n-digit-numbers 1)))))

(deftest test-palindromic?
  (is (not (palindromic? [1 0])))
  (is (palindromic? [1 1]))
  (is (palindromic? [1 9 1]))
  (is (palindromic? [1 3 3 1]))
  (is (palindromic? [1 3 9 3 1]))
  (is (palindromic? [0])))

(deftest test-largest-palindromic-product-of-n-digit-numbers
  (is (= 9009 (last (sort (palindromic-products-of-n-digit-numbers 2))))))

(defn -main []
  (println (last (sort (palindromic-products-of-n-digit-numbers 3)))))

