Puppet::Type.newtype(:staging_file) do
  ensurable

  newparams(:unless) do
    desc 'File is require to exist unless condition exist'
  end

  newparams(:onlyif) do
    desc 'File is require to exist onlyif condition exist'
  end

  newparams(:environment) do
    desc 'Environment variable to pass to exec (such as http_proxy).'
  end

  newparams(:checksum) do
    desc 'checksum for file (.extension indicate checksum value available on remote system)'
  end

  newparams(:checksum_type) do
    desc 'checksum type for file (MD5, SHA1).'
  end

  newparams(:source) do
    desc 'file source.'
  end

  newparams(:username) do
  end
  newparams(:password) do
  end
  newparams(:timeout) do
  end
  newproperty(:user) do
  end
  newproperty(:group) do
  end
  newproperty(:mode) do
  end

  newproperty(:purge) do
    defaultto(:true)
  end

  newparameter(:curl_option) do
    defaultto('')
  end

  newparameter(:wget_option) do
    defaultto('')
  end

  newparameter(:curl_option)
end

