#!/bin/bash

#
# NCCD3 x y1 y2 y3
#
Cy=`./ImgCondComp -tc ctx1 -t $1 -t $2 -t $3 | grep "Total target" | cut -d" " -f4`
Cylx=`./ImgCondComp -rc ctx1 -tc ctx1 -r $1 -t $2 -t $3 -t $4 | grep "Total target" | cut -d" " -f4`

echo "scale = 4; $Cylx / $Cy" | bc -l