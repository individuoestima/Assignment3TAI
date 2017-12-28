#!/bin/bash

erro=0
for s in `seq 1 10`; do
	tarSubject=`printf "%02d" $s`
	echo -n "$tarSubject : "
	for version in 07 ;do
		tar=orl_faces/s$tarSubject/$version.pgm
		score=`java -jar Assignment3TAI.jar $tar $1`
		echo -n "$score "
	done
	printf "\n"
done