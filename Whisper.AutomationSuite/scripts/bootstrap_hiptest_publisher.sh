#!/bin/bash
# bootstrap_hiptest_publisher.sh
# Tim Littlefair, February 2017

# Script to install dependencies to run hiptest
# on an Ubuntu 14.04 LTS instance launched from 
# a Vix reference build machine AMI in AWS.

# Issues which make this difficult:
# 1) The version of Ruby packaged in 14.04 LTS is 1.9.1,
# hiptest-publisher requires at least 2.3.0
# 2) The configure script used when building Ruby from 
# source silently builds without OpenSSL support when
# the prerequisites are not there at configure time, 
# absence of OpenSSL support prevents the Ruby packaged
# manager 'gem' being able to use its default repository
# (which is https, as it should be), and later prevents
# building 'nokogiri' which is a precursor dependency
# of hiptest.
# 3) 'nokogiri' also depends on zlib, again this might
# need to be available before Ruby is built from source,
# it is not obvious that the Ubuntu package required 
# is called 'zlib1g-dev'.

# Relevant links:
# http://askubuntu.com/questions/513369/openssl-installed-but-ruby-unable-to-require-it
# http://www.larshaendler.com/2015/05/20/unable-to-require-openssl-install-openssl-and-rebuild-ruby-on-ubuntu-14-lts/

sudo apt-get update
sudo apt-get install zlib1g-dev
sudo apt-get install openssl
sudo apt-get install libssl-dev

wget https://cache.ruby-lang.org/pub/ruby/2.3/ruby-2.3.0.tar.gz
tar xzvf ruby-2.3.0.tar.gz
cd ruby-2.3.0
./configure --with-openssl
make
sudo make install
cd ..

# The next two lines may not be necessary if 
# ruby builds with openssl support
# sudo gem sources --remove https://rubygems.org/
# sudo gem sources --add https://rubygems.org/

# Installing nokogiri separately may not be 
# necessary - but it used to be the point of failure
sudo gem install nokogiri
sudo gem install hiptest-publisher

# Other things which have been done to this machine 
# to resolve problems with JATS and NIS binding
# Probably not required if the instance runs 
# in an AWS subnet which can talk to 10.0.0.0/24.

# edit /etc/yp.conf to do NIS from auperaunx01
# edit /etc/hosts to add auperaarc02
