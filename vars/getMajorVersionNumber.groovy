#!/usr/bin/env groovy

def call() {
  sh 'git describe | cut -d . -f 1 > major_num.tmp'
  String major_version_number = readFile("major_num.tmp").trim()
  return major_version_number
}
