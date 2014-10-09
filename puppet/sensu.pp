Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }


node default {
	include apt

	rabbitmq_vhost { '/sensu': }
	rabbitmq_user { 'sensu': 
		password => 'changeme', #hiera('rabbitmq_pass'), 
	}
	rabbitmq_user_permissions { 'sensu@/sensu':
		configure_permission => '.*',
		read_permission => '.*',
		write_permission => '.*',
	}
	class {'rabbitmq': } -> 
	class {'redis': } ->
	class {'sensu':
		server => true,
		api => true,
		purge_config => true,
		rabbitmq_user => 'sensu',
		rabbitmq_password => hiera('rabbitmq_pass'),
		rabbitmq_host => hiera('rabbitmq_host'),
		rabbitmq_vhost => '/sensu',
		rabbitmq_port => 5672,
		subscriptions => 'sensu-test',
	}

}