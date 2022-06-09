cd ../
./build.sh
cd deployment
versionhash=$(git rev-parse --short HEAD)
ansible-playbook tasks/deploy.yml -i inventory/inventory_test --ask-pass --ask-become-pass --extra-vars "versionhash=$versionhash"
