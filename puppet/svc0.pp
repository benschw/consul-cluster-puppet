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
		notify       => Service["demo"],
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

	include dnsmasq
	
	dnsmasq::dnsserver { 'forward-zone-consul':
		domain => "consul",
		ip => "127.0.0.1#8600",
	}

	class { 'consul':
		# join_cluster => hiera('join_addr'),
		config_hash => {
			'datacenter' => 'dc1',
			'data_dir'   => '/opt/consul',
			'log_level'  => 'INFO',
			'node_name'  => $::hostname,
			'bind_addr'  => $::ipaddress_eth1,
			'server'     => false,
			'start_join' => [hiera('join_addr')],
		}
	}

	consul::service { 'my-svc':
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
		before       => Consul::Service['my-svc']
	}

}
