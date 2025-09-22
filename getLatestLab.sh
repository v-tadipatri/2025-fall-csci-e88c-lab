#!/bin/bash

week=$1
prefix="core"
if [ "$week" = "" ]
then
	echo "Specify week"
	exit
fi

set -x
rm -Rf mainrepo
#Private repo
git clone git@github.com:v-tadipatri/2024-fall-csci-e88c-lab.git mainrepo
set +x
if [ -d "mainrepo" ]
then
    cd mainrepo
	git checkout after-lab${week}
	fileset=`find . -type f | grep "week${week}lab"`
	mkdir ../${prefix}/src/main/scala/org/cscie88c/${prefix}/week${week}lab
	mkdir ../${prefix}/src/test/scala/org/cscie88c/${prefix}/week${week}lab
	for i in $fileset
	do
		dest=`echo $prefix/$i | sed "s#week#${prefix}/week#"`
		cp -v $i ../$dest 
	done
    cd ..
	git add $prefix
    git status
else
    echo "Could not clone main repo"
fi


