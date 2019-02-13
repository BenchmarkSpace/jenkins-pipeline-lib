#!/usr/bin/env groovy

def call() {
  sh 'git describe | cut -d . -f 1 > major_num.tmp'
  return readFile("major_num.tmp").trim()
}
