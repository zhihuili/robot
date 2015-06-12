cur="`dirname "$0"`"
cur="`cd "$cur"; pwd`"
classpath="$cur/robot.jar"
pid=$cur/robot.pid
if [ -f $pid ]; then
  if kill -0 `cat $pid` > /dev/null 2>&1; then
     echo "ERROR: robot is running now, stop it first." 
     exit 1
  fi
fi
nohup java -cp $classpath com.nana.robot.ui.AIRobot $@ > robot.log 2>&1 < /dev/null &
echo $! > $pid
