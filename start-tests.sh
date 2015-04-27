#!/bin/bash
export LD_LIBRARY_PATH=/usr/lib/jvm/java-7-openjdk-i386/jre/lib/i386/jli/ 
cd /var/www/sizeguide.adambarrell.co.uk/web/app && nohup mvn test &