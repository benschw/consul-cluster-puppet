Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }



node default {

	class { 'consul':
		# join_cluster => '172.20.20.10',
	  	config_hash => {
			'datacenter'  => 'dc1',
			'data_dir'    => '/opt/consul',
			'ui_dir'      => '/opt/consul/ui',
			'client_addr' => '0.0.0.0',
			'log_level'   => 'INFO',
			'node_name'   => 'webui',
			'bind_addr'   => $::ipaddress_eth1,
			'server'      => true,
		}
	}


}
