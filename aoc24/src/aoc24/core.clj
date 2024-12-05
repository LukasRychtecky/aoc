(ns aoc24.core)

(defn transpose
  [m]
  (apply mapv vector m))

(defn parse-input-file
  [cols url]
  (let [content
        (-> url
            slurp
            (clojure.string/split #"\s+"))]
    (->> content
         (map parse-long)
         (partition-all cols)
         transpose)))

(defn day1a
  [url]
  (let [cols (parse-input-file 2 url)]
    (->> cols
         (map sort)
         (cons (comp abs -))
         (apply map)
         (reduce + 0))))

(defn day1b
  [url]
  (let [[xs ys]
        (parse-input-file 2 url)]
    (->> xs
         set
         (select-keys (frequencies ys))
         (map (partial apply *))
         (reduce + 0))))

(comment
  (let [file "resources/aoc24-input.txt"]
    ;; (day1a file)
    (day1b file)))
