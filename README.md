#MusicLibrary

<img src="https://travis-ci.org/Milan26/MusicLibrary.svg?branch=master"/>

##Installation

###Prerequisites

* Maven
* Apache Tomcat
* Apache Derby

####How to prepare Apache Derby (Windows)

* Download and unzip latest release of [apache derby](http://db.apache.org/derby/derby_downloads.html)
* Set [CLASSPATH environment variable](http://db.apache.org/derby/docs/10.0/manuals/getstart/gspr16.html)
* To start the Network Server up on port 1527 use one of the commands below:
```sh
		%DERBY_INSTALL%\bin\startNetworkServer.bat
```
```sh
		java -jar %DERBY_INSTALL%\lib\derbyrun.jar server start
```
