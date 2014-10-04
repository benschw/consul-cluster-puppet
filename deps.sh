#!/bin/sh

rm -rf puppet/modules/*
mkdir -p puppet/modules

git clone git://github.com/puppetlabs/puppetlabs-stdlib.git puppet/modules/stdlib
git clone https://github.com/puppetlabs/puppetlabs-apt puppet/modules/apt
git clone https://github.com/solarkennedy/puppet-consul puppet/modules/consul
git clone git@github.com:nanliu/puppet-staging.git puppet/modules/staging
git clone https://github.com/saz/puppet-dnsmasq.git puppet/modules/dnsmasq