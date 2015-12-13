#!/bin/bash
usage () {
      echo 'Usage: ./test.constant.sh <dns> <output>'
        exit
    }

if [ -z $1 ] || [ -z $2 ]
then usage
fi

# Create the Test Log File and Init Header
touch $2
echo "<h3>Media Application Benchmark Test</h3>" >> $2
#./report.sh starttest roundrobin
echo "<p>Test launched. Please check every minute for update.<p><br/>" >> $2

f=$(date +'%Y-%m-%d %H:%M:%S')
id=$(echo $2 | cut -d'.' -f 2)
echo "<p><b>[Test Start]</b><br>" >> $2
echo "<b>time</b>=$f<br>" >> $2
echo "<b>type</b>=Constant<br>" >> $2
echo "<b>testId</b>=$id<br>" >> $2
echo "<b>testFile</b>=$2<br>" >> $2
echo "<br></p>">> $2

# Init Load Generation Parameters
t=60                                  # Time for unit in load pattern (sec)
rpss=0                                # RPS
load=( 100 100 100 100 100 )

# Run the test
i=0
while [ ${i} -lt 5 ]
do
  # Start Load Generation
  result=`jmeter -n -t /home/ubuntu/MediaApplicationLoadGenerator.jmx -Jhostname=$1 -Jduration=$t -Jthroughput=200000 -Jthreads=${load[$i]}  -Jfilename=pattern_roundrobin`
  # Evaluate the stats for the current minute
  summary=`echo "$result" | grep 'summary =' | tac | sed -n 1p`
  throughput=`echo "$summary" | tr -s ' ' | cut -d ' ' -f7`
  error=`echo "$summary" | tr -s ' ' | cut -d ' ' -f16| tr -d '(' | tr -d ')' | tr -d '%'`
  
  # Get RPS
  rps=`echo "scale=2; ${throughput%/s} * (100 - $error) / 100" | bc`
  if [ -z $rps ]
  then
      rps=0
  fi
  # Increment Time
  i=$(( $i + 1 ))
  echo "<h5>[Minute $i]<h5>" >> $2
  # Print evaluated stats for the minute
  echo "<p>rps=$rps<br>" >> $2
  echo "error_rate=$error<br>" >> $2
  echo "<br></p>">> $2
  # Calculate sum rps
  rpss=$(echo $rpss + $rps | bc)
done
# Print test info used to launch the test
echo "<h4>[Media Application]</h4>" >> $2
echo "dns=$1" >> $2
echo >> $2
echo "<h4>[Test End]</h4>" >> $2
l=$(date +'%Y-%m-%d %H:%M:%S')
echo "time=$l" >> $2
rpss=$(echo $rpss / $i | bc)
echo "<h4>Average rps=$rpss </h4>" >> $2
echo >> $2

echo "<p><b>Test finished. Goodbye!</b></p>" >> $2
