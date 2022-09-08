docker build -f address-service\Dockerfile -t invite/address-service:latest address-service\.
docker build -f amenity-service\Dockerfile -t invite/amenity-service:latest amenity-service\.
docker build -f benefit-service\Dockerfile -t invite/benefit-service:latest benefit-service\.
docker build -f configuration-service\Dockerfile -t invite/configuration-service:latest configuration-service\.
docker build -f discovery-service\Dockerfile -t invite/discovery-service:latest discovery-service\.
docker build -f gateway-service\Dockerfile -t invite/gateway-service:latest gateway-service\.
docker build -f job-worker-service\Dockerfile -t invite/job-worker-service:latest job-worker-service\.
docker build -f member-service\Dockerfile -t invite/member-service:latest member-service\.
docker build -f membership-service\Dockerfile -t invite/membership-service:latest membership-service\.
docker build -f organization-service\Dockerfile -t invite/organization-service:latest organization-service\.
docker build -f process-service\Dockerfile -t invite/process-service:latest process-service\.


docker compose up --wait --timeout 60 elasticsearch
docker compose up --wait --timeout 60 zeebe
docker compose up --wait --timeout 60 operate
docker compose up --wait --timeout 60 tasklist
docker compose up --wait --timeout 60 configuration-service
docker compose up --wait --timeout 60 discovery-service
docker compose up --wait --timeout 60 address-service
docker compose up --wait --timeout 60 amenity-service
docker compose up --wait --timeout 60 benefit-service
docker compose up --wait --timeout 60 member-service
docker compose up --wait --timeout 60 organization-service
docker compose up --wait --timeout 60 membership-service
docker compose up --wait --timeout 60 job-worker-service
docker compose up --wait --timeout 60 process-service
docker compose up --wait --timeout 60 gateway-service