Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }



node default {
		
	# package {["redis-server", "git", "curl", "rubygems", "wget", "mysql-client"]:
	# 		ensure => present,
	# }


	class { 'consul':
		# join_cluster => '172.20.20.9',
	  	config_hash => {
			'datacenter'  => 'dc1',
			'data_dir'    => '/opt/consul',
			'ui_dir'      => '/opt/consul/ui',
			'client_addr' => '0.0.0.0',
			'log_level'   => 'INFO',
			'node_name'   => 'webui',
			'bind_addr'   => '172.20.20.12',
			'server'      => true,
		}
	}


}
