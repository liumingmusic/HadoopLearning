#!/usr/bin/env bash

MODULE=$1
FIRST_PARAMETER=$2
TWO_PARAMETER=$3
LEN=$#

if [ "$MODULE" = "hdfs" ]
then
    if [ "$LEN" == 3 ]
    then
        java -jar StageOnePractice-1.0-SNAPSHOT-jar-with-dependencies.jar $MODULE $FIRST_PARAMETER $TWO_PARAMETER
    else
        echo -e "
Input error. Please input:
    app help hdfs
Can view the help document"
    fi
elif [ "$MODULE" = "mr" ]
then
    if [ "$LEN" == 3 ]
    then
        java -jar StageOnePractice-1.0-SNAPSHOT-jar-with-dependencies.jar $MODULE $FIRST_PARAMETER $TWO_PARAMETER
    else
        echo -e "
Input error. Please input:
    app help mr
Can view the help document"
    fi
elif [ "$MODULE" = "hbase" ]
then
    if [ "$LEN" == 2 ]
    then
        java -jar StageOnePractice-1.0-SNAPSHOT-jar-with-dependencies.jar $MODULE $FIRST_PARAMETER
    else
        echo -e "
Input error. Please input:
    app help hbase
Can view the help document"
    fi

elif [ "$MODULE" = "help" ]
then
    if [ "$FIRST_PARAMETER" = "hdfs" ]
    then
        echo -e "
Usage: ./app hdfs

Commands:
    hdfs  </file/src,/file//dest>  (upload file to hdfs)

Example:
    ./app.sh hdfs /home/city.json /city/list
"
    elif [ "$FIRST_PARAMETER" = "mr" ]
    then
        echo -e "
Usage: ./app mr

Commands:
    ./app.sh mr src dest (Run mr app)
    ./app.sh mr truncate src dest (Run mr app and clean hbase table)

Example:
    ./app.sh hdfs /home/city.json /city/list
"
    elif [ "$FIRST_PARAMETER" = "hbase" ]
    then
        echo -e "
Usage: ./app mr

Commands:
    ./app.sh hbase '000000'  (Search the current areaId of child nodes below )
    ./app.sh hbase '000000,110000'  (Count the current areaId of child nodes below)

Example:
    ./app.sh '000000'
    ./app.sh '000000,110000'
"
    else
        echo -e "
Usage: ./app <hdfs|mr|hbase>

Commands:
    hdfs  </file/src,/file//dest>  upload file to hdfs
    mr    [truncate] <src,dest>  Run mr app write to hbase
    hbase <\"000000\"|\"000000,111000\">  Search the current areaId of child nodes below

Example:
    ./app.sh hdfs /home/city.json /city/list
    ./app.sh mr src dest
    ./app.sh mr truncate src dest
    ./app.sh '000000'
    ./app.sh '000000,110000'

Documentation:
    https://liumingmusic.github.io/HadoopLearning/#/

Development:
    https://github.com/liumingmusic/HadoopLearning
"
    fi
else
    echo -e "
Input error. Please input:
    app help
Can view the help document"
fi
