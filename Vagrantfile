# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  
  # config.vm.box_url = "http://netmgr11.bvops.net/images/debian7-base.box"
  # config.vm.box = "debian7-base"

  config.vm.box_url = "https://cloud-images.ubuntu.com/vagrant/trusty/current/trusty-server-cloudimg-amd64-vagrant-disk1.box"
  config.vm.box = "trusty64"

  # consul0 ====================================================================
  config.vm.define "consul0" do |consul0|

    consul0.vm.hostname = "consul0"
    consul0.vm.network "private_network", ip: "172.20.20.9"

    consul0.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "server.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
        # '--hiera_config=/vagrant/hiera/server0.yaml',
      ]
    end
  end
  # end ========================================================================

  # consul1 ====================================================================
  config.vm.define "consul1" do |consul1|

    consul1.vm.hostname = "consul1"
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

    consul2.vm.hostname = "consul2"
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

    webui.vm.hostname = "webui"
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

  # svc0 =======================================================================
  config.vm.define "svc0" do |svc0|

    svc0.vm.hostname = "svc0"
    svc0.vm.network "private_network", ip: "172.20.20.13"

    svc0.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "svc0.pp"
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

