#!/usr/bin/env groovy

def call() {
  String major_version_number = "-1"
  sh 'git describe | cut -d . -f 1 > major_num.tmp'
  major_version_number = readFile("major_num.tmp").trim()
  return major_version_number
}
