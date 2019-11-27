#!/bin/bash

function moveGeneratedFiles() {
    exit 0;
}

function build() {
  exit 0;
}



# This script is responsible for building the rust library and will be called by the gradle project
if [ $# -eq 3 ]; then

  # The first argument will be the current directory
  workingDirectory=$1
  # The second argument will be the output generated library directory
  generatedLibraryDirectory=$2

  buildRoot=$3

  # Move files from template path
  moveGeneratedFiles
  # Build library binary
  build
  # Build any docs

  exit 0
else
  echo 'Supplied the incorrect number of arguments'
  exit 255
fi