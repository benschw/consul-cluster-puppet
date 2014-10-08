Exec { path => "/usr/bin:/usr/sbin:/bin:/sbin" }


node default {
  include apt
  
  class { 'rabbitmq':
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
    api => true,
    purge_config => true,
    rabbitmq_user => 'sensu',
    rabbitmq_password => 'password',
    rabbitmq_host => '172.20.20.16',
    rabbitmq_vhost => '/sensu',
    rabbitmq_port => 5672,
    subscriptions => 'sensu-test',
  }

}