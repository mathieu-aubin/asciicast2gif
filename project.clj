(defproject asciicast2gif "0.2.2-SNAPSHOT"
  :description "asciinema GIF generator"
  :url "https://github.com/asciinema/asciicast2gif"
  :license {:name "MIT"}

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async "0.4.500"]
                 [prismatic/schema "1.1.12"]
                 [org.clojure/core.match "0.3.0"]
                 [cljsjs/nodejs-externs "1.0.4-1"]
                 [reagent "0.8.1"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :source-paths ["src" "asciinema-player/src" "asciinema-player/vt/src"]
  :resource-paths ["resources" "asciinema-player/resources" "asciinema-player/vt/resources"]

  :clean-targets ^{:protect false} ["target" "main.js" "page/page.js"]

  :cljsbuild {:builds {:main {:source-paths ["src"]
                              :compiler {:output-to "main.js"
                                         :foreign-libs [{:file "codepoint-polyfill.js"
                                                         :provides ["asciinema.vt.codepoint-polyfill"]}]
                                         :optimizations :advanced
                                         :pretty-print false
                                         :elide-asserts true
                                         :target :nodejs
                                         :externs ["externs/child_process.js"]
                                         :main "asciinema.gif.main"}}
                       :page {:source-paths ["src"]
                              :compiler {:output-to "page/page.js"
                                         :foreign-libs [{:file "codepoint-polyfill.js"
                                                         :provides ["asciinema.vt.codepoint-polyfill"]}]
                                         :optimizations :advanced
                                         :pretty-print false
                                         :elide-asserts true
                                         :main "asciinema.gif.page"}}}})
