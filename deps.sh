#!/bin/sh

rm -rf puppet/modules/*
mkdir -p puppet/modules

git clone git://github.com/puppetlabs/puppetlabs-stdlib.git puppet/modules/stdlib
git clone https://github.com/puppetlabs/puppetlabs-apt puppet/modules/apt
git clone https://github.com/solarkennedy/puppet-consul puppet/modules/consul
git clone git@github.com:nanliu/puppet-staging.git puppet/modules/staging

git clone https://github.com/rlex/puppet-dnsmasq.git puppet/modules/dnsmasq
git clone https://github.com/puppetlabs/puppetlabs-concat.git puppet/modules/concat

git clone https://github.com/example42/puppi.git puppet/modules/puppi
git clone https://github.com/example42/puppet-redis.git puppet/modules/redis
git clone https://github.com/puppetlabs/puppetlabs-rabbitmq.git puppet/modules/rabbitmq
git clone https://github.com/sensu/sensu-puppet.git puppet/modules/sensu

