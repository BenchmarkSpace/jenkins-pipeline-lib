def call() {
  sh 'git describe | cut -d . -f 2 > minor_num.tmp'
  String minor_version_number = readFile("minor_num.tmp").trim()
  return minor_version_number
}
