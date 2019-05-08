#!/usr/bin/env groovy

/**
* Delete the temporary build tag that we created
*/
def call() {
	echo 'Getting build folder name'
	def buildFolder = ""
	def uppercaseb = sh(script: "ls | grep Build", returnStatus: true)
	if(uppercaseb == 0){
		buildFolder = "Build"	
	}
	else{
		buildFolder = "build"
	}
	
	echo "Build folder is $buildFolder"
	echo 'Checking clang-format output'
	
	
	sh("clang-format -style=file -i -fallback-style=none ../src/*.(c|h|cpp|hpp)")
	sh("git diff > clang_format.patch")
	sh("if [ ! -s clang_format.patch ];	then rm clang_format.patch; fi")
	

	def exists = fileExists "${buildFolder}/clang_format.patch"
	def success = true

	if(exists)
	{
		// Does the file exist? If so, changes are needed
		echo 'clang-format indicates formatting changes are required. Please check the build artifacts to see the clang-format patch.'
		archiveArtifacts "${buildFolder}/clang_format.patch"

		success = false
	}

	reportFormatStatus(success)
}

