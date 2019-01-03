#!/usr/bin/env groovy

/**
* Delete the temporary build tag that we created
*/
def call() {
	echo 'Checking clang-format output'
	sh('make -C Build/ format-check')

	def exists = fileExists 'Build/clang_format.patch'
	def success = true

	if(exists)
	{
		// Does the file exist? If so, changes are needed
		echo 'clang-format indicates formatting changes are required. Please check the build artifacts to see the clang-format patch.'
		archiveArtifacts 'Build/clang_format.patch'

		success = false
	}

	reportFormatStatus(success)
}

