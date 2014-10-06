Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }


stage { 'preinstall':
  before => Stage['main']
}
 
class apt_get_update {
  exec { 'apt-get -y update': }
}
 
class { 'apt_get_update':
  stage => preinstall
}

import "classes/*"

node default {
	include springboot
	include consulclient

	#
	# Configure Service with Consul (including health check)
	#

	consul::service { 'foo-svc':
		tags => ['actuator'],
		port => 8080,
		check_script => '/opt/health.sh',
		check_interval => '5s',
	}

	file { '/opt/health.sh':
		path         => '/opt/health.sh',
		ensure       => present,
		mode         => 0755,
		source       => '/vagrant/demo/health.sh',
		before       => Consul::Service['foo-svc']
	}

}
