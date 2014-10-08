Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }

node default {
  include apt
  
  file { '/etc/rabbitmq/ssl/server_key.pem':
    source => '/vagrant/sensu-keys/server_key.pem',
  }
  file { '/etc/rabbitmq/ssl/server_cert.pem':
    source => '/vagrant/sensu-keys/server_cert.pem',
  }
  file { '/etc/rabbitmq/ssl/cacert.pem':
    source => '/vagrant/sensu-keys/cacert.pem',
  }
  class { 'rabbitmq':
    ssl_key => '/etc/rabbitmq/ssl//server_key.pem',
    ssl_cert => '/etc/rabbitmq/ssl//server_cert.pem',
    ssl_cacert => '/etc/rabbitmq/ssl//cacert.pem',
    ssl => true,
  }
  rabbitmq_vhost { '/sensu': }
  rabbitmq_user { 'sensu': password => 'password' }
  rabbitmq_user_permissions { 'sensu@/sensu':
    configure_permission => '.*',
    read_permission => '.*',
    write_permission => '.*',
  }
  class {'redis': }
  class {'sensu':
    server => true,
    purge_config => true,
    rabbitmq_password => 'password',
    rabbitmq_ssl_private_key => "/vagrant/sensu-keys/client_key.pem",
    rabbitmq_ssl_cert_chain => "/vagrant/sensu-keys/client_cert.pem",
    rabbitmq_host => 'sensu.local',
    rabbitmq_port => 5671,
    subscriptions => 'sensu-test',
  }
}