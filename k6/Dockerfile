FROM loadimpact/k6

COPY k6.js /script.js

ENTRYPOINT ["k6", "run", "/script.js"]

# docker build . -t qoosdd89382/k6-rabbitmq-container
# kind load docker-image qoosdd89382/k6-rabbitmq-container

# kubectl run --image=qoosdd89382/k6-rabbitmq-container --image-pull-policy=Never k6-rabbitmq-container
# kubectl delete --force po k6-rabbitmq-container