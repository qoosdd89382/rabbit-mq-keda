# 前置動作

必須先確認：
1. branch 在 `takenumber` 再打包成 image
2. rabbitmq 是起來的狀態
3. configmap 連線資訊都正確

# 部署

需要 apply 的項目：
- consumer
  - config map
  - deployment
- producer
  - config map
  - deployment

```shell
kubectl apply -f consumer-config.yaml
kubectl apply -f consumer-deploy.yaml
kubectl apply -f producer-config.yaml
kubectl apply -f producer-deploy.yaml
```