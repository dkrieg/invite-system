docker build -f address-service/Dockerfile -t invite/address-service:latest address-service/.
docker build -f amenity-service/Dockerfile -t invite/amenity-service:latest amenity-service/.
docker build -f benefit-service/Dockerfile -t invite/benefit-service:latest benefit-service/.
docker build -f community-service/Dockerfile -t invite/community-service:latest community-service/.
docker build -f configuration-service/Dockerfile -t invite/configuration-service:latest configuration-service/.
docker build -f discovery-service/Dockerfile -t invite/discovery-service:latest discovery-service/.
docker build -f gateway-service/Dockerfile -t invite/gateway-service:latest gateway-service/.
docker build -f job-worker-service/Dockerfile -t invite/job-worker-service:latest job-worker-service/.
docker build -f line-of-business-service/Dockerfile -t invite/line-of-business-service:latest line-of-business-service/.
docker build -f market-service/Dockerfile -t invite/market-service:latest market-service/.
docker build -f member-service/Dockerfile -t invite/member-service:latest member-service/.
docker build -f membership-service/Dockerfile -t invite/membership-service:latest membership-service/.
docker build -f club-service/Dockerfile -t invite/club-service:latest club-service/.
docker build -f process-service/Dockerfile -t invite/process-service:latest process-service/.
docker build -f provider-group-service/Dockerfile -t invite/provider-group-service:latest provider-group-service/.
docker build -f reservation-service/Dockerfile -t invite/reservation-service:latest reservation-service/.
docker build -f reservation-ui/Dockerfile -t invite/reservation-ui:latest reservation-ui/.

docker compose --env-file api_key.txt up --wait --timeout 60 elasticsearch
docker compose --env-file api_key.txt up --wait --timeout 60 zeebe
docker compose --env-file api_key.txt up --wait --timeout 60 operate
docker compose --env-file api_key.txt up --wait --timeout 60 tasklist
docker compose --env-file api_key.txt up --wait --timeout 60 configuration-service
docker compose --env-file api_key.txt up --wait --timeout 60 discovery-service
docker compose --env-file api_key.txt up --wait --timeout 60 address-service
docker compose --env-file api_key.txt up --wait --timeout 60 amenity-service
docker compose --env-file api_key.txt up --wait --timeout 60 benefit-service
docker compose --env-file api_key.txt up --wait --timeout 60 community-service
docker compose --env-file api_key.txt up --wait --timeout 60 line-of-business-service
docker compose --env-file api_key.txt up --wait --timeout 60 market-service
docker compose --env-file api_key.txt up --wait --timeout 60 provider-group-service
docker compose --env-file api_key.txt up --wait --timeout 60 member-service
docker compose --env-file api_key.txt up --wait --timeout 60 club-service
docker compose --env-file api_key.txt up --wait --timeout 60 membership-service
docker compose --env-file api_key.txt up --wait --timeout 60 reservation-service
docker compose --env-file api_key.txt up --wait --timeout 60 job-worker-service
docker compose --env-file api_key.txt up --wait --timeout 60 process-service
docker compose --env-file api_key.txt up --wait --timeout 60 gateway-service
docker compose --env-file api_key.txt up --wait --timeout 60 reservation-ui