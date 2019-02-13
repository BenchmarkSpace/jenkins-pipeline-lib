def call() {
  sh 'git describe | cut -d . -f 2 > minor_num.tmp'
  return readFile("minor_num.tmp").trim()
}
