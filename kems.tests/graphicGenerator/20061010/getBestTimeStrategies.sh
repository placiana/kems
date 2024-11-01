csvFile=$1
biggerProblem=`./sortSzTm.sh $csvFile | tail -1 | awk '{print $1}'`
nSolvedBigger=`./sortSzTm.sh $csvFile | grep -c $biggerProblem`
echo "Bigger problem instance solved: "$biggerProblem
#echo $nSolvedBigger


### BEST IN TIME  WHO SOLVED BIGGER
# troquei 9 por 7
best=`./sortTmSz.sh $csvFile | tail -$nSolvedBigger | head -1 | awk '{print $5 " ; " $7 " ; " $9}'`
#echo $best

#moreAfterBest is the number of best pairs shown
if [ "$2" = "" ]
 then
  moreAfterBest=2
 else
  moreAfterBest=$2
  moreAfterBest=$((moreAfterBest-1))
fi

# troquei 9 por 7 e vice-versa
echo "./sortTmSz.sh $csvFile | grep $biggerProblem | grep -A $moreAfterBest \"$best\" | awk '{print \$5 \" & \" \$7 \" & \" \$9 }'" > __tmp.sh
#cat __tmp.sh
chmod +x __tmp.sh
#
echo
echo "best in time who solved bigger instance:"
echo "pair & best time & size"
./__tmp.sh
rm -rf ./__tmp.sh



### WORST IN TIME  WHO SOLVED BIGGER
# troquei 9 por 7
worst=`./sortTmSz.sh $csvFile | tail -1 | awk '{print $5 " ; " $7 " ; " $9 }'`
#echo $worst


#moreAfterWorst is the number of worst pairs shown
if [ "$3" = "" ]
 then
  moreAfterWorst=2
 else
  moreAfterWorst=$3
  moreAfterWorst=$((moreAfterWorst-1))
fi

# troquei 9 por 7 e vice-versa
echo "./sortTmSz.sh $csvFile | grep $biggerProblem | grep -B $moreAfterWorst \"$worst\" | awk '{print \$5 \" & \" \$7 \" & \" \$9 }'" > __tmp.sh
#cat __tmp.sh
chmod +x __tmp.sh
#
echo
echo "worst in time who solved bigger instance:"
echo "pair & worst time & size"
./__tmp.sh
rm -rf ./__tmp.sh


##  FIRST NOT SOLVED AND BIGGER ALL SOLVED
echo 

firstNotSolved=`cat $csvFile | grep Error | head -1 | awk '{print $1}'`


### MUDAR NO OUTRO - testar com um sem erros
# O resto n�o faz sentido 
if [ "$firstNotSolved" == "" ]
then
  echo "All instances were solved by all pairs!"
  exit
else
  echo "First not solved:"
  echo $firstNotSolved
fi






#biggerAllSolved
biggerAllSolved=`cat $csvFile | grep $firstNotSolved -1 | head -1 | awk '{print $1}'`
echo
echo "Bigger all solved:"
echo $biggerAllSolved




### BEST IN TIME  BETWEEN THOSE WHO SOLVED ALL
best=`./sortTmSz.sh $csvFile | grep $biggerAllSolved  | head -1 | awk '{print $5 " ; " $7 " ; " $9 }'`
#echo $best


#moreAfterBest is the number of best pairs shown
if [ "$4" = "" ]
 then
  moreAfterBest=2
 else
  moreAfterBest=$4
  moreAfterBest=$((moreAfterBest-1))
fi




echo "./sortTmSz.sh $csvFile | grep $biggerAllSolved | grep -A $moreAfterBest \"$best\" | awk '{print \$5 \" & \" \$7 \" & \" \$9 }'" > __tmp.sh

#cat __tmp.sh
chmod +x __tmp.sh
#
echo
echo "best in time between those who solved the bigger all solved:"
echo "pair & best time & size"
./__tmp.sh
rm -rf ./__tmp.sh



#WORST IN TIME BETWEEN THOSE WHO SOLVED ALL

worst=`./sortTmSz.sh $csvFile | grep $biggerAllSolved | tail -1 | awk '{print $5 " ; " $7 " ; " $9 }'`
#echo $worst


#moreAfterWorst is the number of worst pairs shown
if [ "$5" = "" ]
 then
  moreAfterWorst=2
 else
  moreAfterWorst=$5
  moreAfterWorst=$((moreAfterWorst-1))
fi



#echo "./sortTmSz.sh $csvFile | grep $biggerProblem | grep -B $moreAfterWorst \"$worst\" | awk '{print \$5 \" & \" \$7 \" & \" \$9 }'" > __tmp.sh
echo "./sortTmSz.sh $csvFile | grep $biggerAllSolved | grep -B $moreAfterWorst \"$worst\" | awk '{print \$5 \" & \" \$7 \" & \" \$9 }'" > __tmp.sh
#cat __tmp.sh
chmod +x __tmp.sh
#
echo
echo "worst in time between those who solved the bigger all solved:"
echo "pair & worst time & size"
./__tmp.sh
rm -rf ./__tmp.sh


