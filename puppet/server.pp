Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }



node default {

	class { 'consul': 
		# join_cluster => '172.20.20.10',
		config_hash => {
			'datacenter' => 'dc1',
			'data_dir'   => '/opt/consul',
			'log_level'  => 'INFO',
			'node_name'  => $::hostname,
			'bind_addr'  => $::ipaddress_eth1,
			'bootstrap_expect' => 3,
			'server'     => true,
		}
	}

}
