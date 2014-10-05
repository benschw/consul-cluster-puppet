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

node default {
	

	package {["openjdk-7-jdk"]:
		ensure => present,
	}


	file { '/opt/demo.jar':
		path         => '/opt/demo.jar',
		ensure       => present,
		source       => '/vagrant/demo/build/libs/demo-0.1.0.jar',
	}

	file { '/etc/init/demo.conf':
		path         => '/etc/init/demo.conf',
		ensure       => present,
		source       => '/vagrant/demo/demo-init.conf',
	}

	service { "demo":
	    ensure  => "running",
	    enable  => "true",
		require => [
			Package["openjdk-7-jdk"],
			File["/etc/init/demo.conf"],
			File["/opt/demo.jar"],
		]
	}

	class { 'consul':
		# join_cluster => '172.20.20.9',
		config_hash => {
			'datacenter' => 'dc1',
			'data_dir'   => '/opt/consul',
			'log_level'  => 'INFO',
			'node_name'  => 'svc0',
			'bind_addr'  => '172.20.20.13',
			'server'     => false,
		}
	}

	file { '/opt/health.sh':
		path         => '/opt/health.sh',
		ensure       => present,
		mode         => 0755,
		source       => '/vagrant/demo/health.sh',
		before       => Consul::Service['my-svc']
	}

	consul::service { 'my-svc':
		tags => ['actuator'],
		port => 8080,
		check_script => '/opt/health.sh',
		check_interval => '5s',
	}

	include dnsmasq
	
	dnsmasq::dnsserver { 'forward-zone-consul':
		domain => "consul",
		ip => "127.0.0.1#8600",
	}

}
