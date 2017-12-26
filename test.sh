#!/bin/bash

for s in `seq 1 40`; do
	tarSubject=`printf "%02d" $s`
	echo -n "$tarSubject : "
	for version in 04 05 06 07 08 09 10;do
		tar=orl_faces/s$tarSubject/$version.pgm
		score=`java -jar Assignment3TAI.jar $tar`
		echo -n "$score "
	done
	printf "\n"
done