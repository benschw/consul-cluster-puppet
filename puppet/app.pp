Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }

# fix dnsmasq, which looks for /bin/test
file { '/bin/test':
   ensure => 'link',
   target => '/usr/bin/test',
}

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
	include apt

	class { 'spring_boot_app': 
		service_name => hiera('svc_name'),
		jar_path     => '/vagrant/demo/build/libs/demo-0.1.0.jar',
		health_path  => '/vagrant/demo/health.py',
	}->
	class { 'consul_client': 
		service_name => hiera('svc_name'),
		health_path  => '/vagrant/demo/health.py',
	}

}
