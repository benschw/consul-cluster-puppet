class springboot {

	#
	# Spring Boot Application
	#

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

}