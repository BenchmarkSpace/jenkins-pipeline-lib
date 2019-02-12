def call() {
  String minor_version_number = "-1"
  sh 'git describe | cut -d . -f 2 > minor_num.tmp'
  minor_version_number = readFile("minor_num.tmp").trim()
  return minor_version_number
}
