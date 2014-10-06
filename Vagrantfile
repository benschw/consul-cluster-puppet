# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  
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

  # demo =======================================================================
  config.vm.define "demo" do |demo|

    demo.vm.hostname = "demo"
    demo.vm.network "private_network", ip: "172.20.20.13"

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

  # foosvc0 ====================================================================
  config.vm.define "foosvc0" do |foosvc0|

    foosvc0.vm.hostname = "foosvc0"
    foosvc0.vm.network "private_network", ip: "172.20.20.14"

    foosvc0.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "foosvc.pp"
      puppet.options = [
        # '--verbose',
        # '--debug',
      ]
    end
  end
  # end ========================================================================

  # foosvc0 ====================================================================
  config.vm.define "foosvc1" do |foosvc1|

    foosvc1.vm.hostname = "foosvc1"
    foosvc1.vm.network "private_network", ip: "172.20.20.15"

    foosvc1.vm.provision :puppet do |puppet|
      puppet.hiera_config_path = "hiera/hiera.yaml"
      puppet.manifests_path = "puppet"
      puppet.module_path    = "puppet/modules"
      puppet.manifest_file  = "foosvc.pp"
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

