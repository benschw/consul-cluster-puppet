Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }


node default {  
	include apt
  
	class { 'uchiwa':
	}
	uchiwa::api { ' API 1':
		host => hiera('sensu_host'),
	}

}