#!/bin/bash

set -x
rm -Rf mainrepo
git clone https://github.com/esumitra/2025-fall-csci-e88c.git mainrepo
set +x
if [ -d "mainrepo" ]
then
    cd mainrepo
    rm -Rf .git
    ls *.* | grep -v README.md | xargs -I {} cp -v {} ..
	dirset=`find . -type d -maxdepth 1 | grep -v devcontainer | grep "/"`
	for i in $dirset
	do
		cp -a $i ..
	done
    cd ..
    git add $dirset
    git status
else
    echo "Could not clone main repo"
fi


