(ns qualityclj.imports.db
  (:require [qualityclj.models.db :as db]
            [clojure.java.io :as io]
            [clojure.string :as s])
  (:import java.io.File))

(def repo-path "repos")

(defn import-project
  "Given a username and a project name, import it into the database.

  Note: in it's current form, this assumes that everything that's
  considered source code in need of importing is located under the
  'src' folder at the root of the repository.

  TODO: Issue #20. Parse the source directory defined in project.clj
  and use that for importing.

  TODO: Issue #21. This currently only targets Clojure src files, not
  ClojureScript, or test files."
  [uri user project]
  (let [src-folder "src"
        src-path (io/file (s/join File/separator
                                  [repo-path user project src-folder]))
        files (filter #(and (.isFile %) (.endsWith (.getPath %) "clj"))
                      (file-seq src-path))]
    (db/import-repo uri user project files)))
