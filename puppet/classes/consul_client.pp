class consul_client (
	$service_name = 'unknown',
	$health_path  = 'unknown',
) {

	#
	# DnsMasq to forward *.consul requests to local Consul DNS server
	#

	include dnsmasq
	
	dnsmasq::dnsserver { 'forward-zone-consul':
		domain => "consul",
		ip     => "127.0.0.1#8600",
	}

	#
	# Consul Client Agent
	#

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

	consul::service { $service_name:
		tags           => ['actuator'],
		port           => 8080,
		check_script   => $health_path,
		check_interval => '5s',
	}

}