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
	include apt

	class { 'spring_boot_app': 
		jar_path    => '/vagrant/demo/build/libs/demo-0.1.0.jar',
		health_path => '/vagrant/demo/health.py',
	}->
	class { 'consul_client': 
		service_name => 'demo',
		health_path  => '/vagrant/demo/health.py',
	}

}
