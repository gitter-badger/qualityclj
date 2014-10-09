(ns qualityclj.routes.home
  (:require [qualityclj.views.layout :as layout]
            [clojure.java.io :as io]
            [compojure.core :refer :all]))

#_(defn read-kibit-report-from-file
  "Reads in a plain-text kibit-report-to-file."
  [file]
  (with-open [rdr (io/reader (io/resource file))]
    (doseq [line (line-seq rdr)]
      (println line))))

#_(defn write-kibit-report-to-file
  "Write a check-map in plain text to specified file."
  [output-file check-map]
  (let [{:keys [file line expr alt]} check-map]
    (with-open [wrtr (io/writer (io/file output-file))]
      (.write wrtr (pr-str check-map))
      (.write wrtr (str "\n")))))

#_(defn run-kibit-over-file
  "Run kibit over the provided source file and output to the provided
  file location. For now, both must be located in resources"
  [source-file output-file]
  (check/check-file (io/resource source-file)
                    :reporter (partial write-kibit-report-to-file
                                       (io/resource output-file))))

(defn home []
  (layout/common
   [:div#jumbo]
   [:div.container-fluid
    [:h2 "Sample"]]))

(defn about []
  (layout/common
   [:div#header.container-fluid
    [:h2 "About"]
    [:p "Quality Clojure will take a Clojure git repository, import it
    into Codeq, and run a series of linters against it. The linters
    include kibit, eastwood, and bikeshed. There's also the capability
    to add user comments to different parts of the file, or to other
    notes generated by a linter. Users can flag a note or comment for
    various reasons as well."]
    [:p "Quality Clojure was envisioned as a contender for the 2014
   Clojure Cup, but sadly was not finished in time to show off! The
   team plans on completing the work that they've started, but in the
   meantime, the main page shows an example of what Quality Clojure
   might eventually look like."]
    [:p "This site is (mostly) static, aside from the front-end
    Clojurescript adding information about the file displayed on the
    main page, and putting the note bubbles in the approximately
    correct location."]]  ))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/about" [] (about)))
