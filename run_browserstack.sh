#!/bin/bash
ENV="local"

pids=`pidof BrowserStackLocal`
if [ pids ]; then
  echo "BrowserStackLocal process found."
  else
  echo "BrowserStackLocal process not found. You must run 'run_browserstackbinary.sh' in another terminal first."
  exit -1
fi

if [ "$1" = "dev" ] || [ "$1" = "qa" ] || [ "$1" = "staging" ]
then
    ENV="$1"
fi
echo "Environment : $ENV"

# Use this to choose which browser configurations to run against.
#declare -a setups=("")

declare -a setups=("BS_Win8_Firefox_v58" )

#examples:
# "BS_Android_GalaxyS8_v7"
# "BS_ElCapitan_Chrome_v64"
# "BS_ElCapitan_Firefox_v58"
# "BS_ElCapitan_Safari_v9"
# "BS_Sierra_Chrome_v64"
# "BS_Win7_Chrome_v64"
# "BS_Win7_Firefox_v58"
# "BS_Win7_IE_v11"
# "BS_Win8_Chrome_v64"
# "BS_Win8_Firefox_v58"
# "BS_Win8_IE_v10"
# "BS_Win10_Chrome_v64"
# "BS_Win10_Edge_v16"
# "BS_Win10_IE_v11"
# "BS_Yosemite_Chrome_v64"
# "BS_Yosemite_Firefox_v58"
# "iPad_Air_v8_3"
# "iPhone_iOS_v10"
# "Samsung_S6_v5"
# "Samsung_S7_v6"
# "Samsung_S8_v7"

#
# The following devices do not work with automation and need to be manually tested:
#   declare -a setups=( "iPad_5th_v11", "iPad_Mini_v7", "BS_Win10_Firefox_v58" "BS_Yosemite_Safari_v8")
#

for setup in "${setups[@]}"; do
    echo "******************** Loading config from $setup.json ********************"
    mvn -Dlogback.configurationFile=logback.xml -Dbrowser=browserstack -DtestDevice="$setup" -Denvironment="$ENV" -Dtest=RunBrowserStackTests
done