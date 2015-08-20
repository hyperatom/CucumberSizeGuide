VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.box = "precise64"
  config.vm.box_url   = "http://files.vagrantup.com/precise64.box"

  config.vm.network "forwarded_port", guest: 9090, host: 9090
  config.vm.network "private_network", ip: "192.168.50.51"
  config.vm.network "public_network"

  config.vm.synced_folder "./", "/vagrant"

  config.vm.provision :ansible do |ansible|
      ansible.limit           = 'all'
      ansible.playbook        = "provisioning/playbook.yml"
      ansible.inventory_path  = "provisioning/ansible_hosts"
  end

end
