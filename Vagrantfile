# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  
  config.vm.box_url = "https://cloud-images.ubuntu.com/vagrant/trusty/current/trusty-server-cloudimg-amd64-vagrant-disk1.box"
  config.vm.box = "trusty64"

  # consul0 ====================================================================
  config.vm.define "consul0" do |consul0|

    consul0.vm.hostname = "consul0.local"
    consul0.vm.network "private_network", ip: "172.20.20.9"

    consul0.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "server.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # consul1 ====================================================================
  config.vm.define "consul1" do |consul1|

    consul1.vm.hostname = "consul1.local"
    consul1.vm.network "private_network", ip: "172.20.20.10"

    consul1.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "server.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # consul2 ====================================================================
  config.vm.define "consul2" do |consul2|

    consul2.vm.hostname = "consul2.local"
    consul2.vm.network "private_network", ip: "172.20.20.11"

    consul2.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "server.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # web ui =====================================================================
  config.vm.define "webui" do |webui|

    webui.vm.hostname = "webui.local"
    webui.vm.network "private_network", ip: "172.20.20.12"

    webui.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "webui.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # sensu server ===============================================================
  config.vm.define "sensu" do |sensu|

    sensu.vm.hostname = "sensu.local"
    sensu.vm.network "private_network", ip: "172.20.20.13"

    sensu.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "sensu.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # sensu dashboard ============================================================
  config.vm.define "uchiwa" do |uchiwa|

    uchiwa.vm.hostname = "uchiwa.local"
    uchiwa.vm.network "private_network", ip: "172.20.20.14"

    uchiwa.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "uchiwa.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # demo =======================================================================
  config.vm.define "demo" do |demo|

    demo.vm.hostname = "demo.local"
    demo.vm.network "private_network", ip: "172.20.20.20"

    demo.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "demo.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # foo0 =======================================================================
  config.vm.define "foo0" do |foo0|

    foo0.vm.hostname = "foo0.local"
    foo0.vm.network "private_network", ip: "172.20.20.21"

    foo0.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "foo.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # foo1 =======================================================================
  config.vm.define "foo1" do |foo1|

    foo1.vm.hostname = "foo1.local"
    foo1.vm.network "private_network", ip: "172.20.20.22"

    foo1.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "foo.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  config.vm.provider :virtualbox do |vb|
    vb.customize ["modifyvm", :id, "--memory", "512"]
  end

end

