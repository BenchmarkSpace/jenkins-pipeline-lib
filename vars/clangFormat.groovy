#!/usr/bin/env groovy

/**
* Delete the temporary build tag that we created
*/
def call() {

	echo 'Checking clang-format output'


	sh("clang-format -style=file -i -fallback-style=none ./src/*.[ch]")
	sh("git diff > clang_format.patch")
	sh("if [ ! -s clang_format.patch ];	then rm clang_format.patch; fi")


	def exists = fileExists "clang_format.patch"
	def success = true

	if(exists)
	{
		// Does the file exist? If so, changes are needed
		echo 'clang-format indicates formatting changes are required. Please check the build artifacts to see the clang-format patch.'
		archiveArtifacts "clang_format.patch"

		success = false
	}

	reportFormatStatus(success)
}
