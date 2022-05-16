docker login --username=万仁杰 --password=pyczzfbjt939401 registry.cn-shanghai.aliyuncs.com
 
repo_address="registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08"

# 将本地打包好的镜像打一个tag
#docker tag 4389c4f4800f registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:eureka-server
#docker tag 06f08574381f registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:gateway
#docker tag 007239ffa012 registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:mysql5
docker tag 5b0611492511 registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:onlinemart-backstage
#docker tag b9911e5a9694 registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:onlinemart-vue
#docker tag bbc4b0da4e68 registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:user-service
#docker tag 8ab13ff00fa0 registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:product-service
#docker tag 47efd8933534 registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:orders-service


# 推到自己的阿里云镜像仓库
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:eureka-server
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:gateway
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:mysql5
docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:onlinemart-backstage
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:onlinemart-vue
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:user-service
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:product-service
#docker push registry.cn-shanghai.aliyuncs.com/fdu_adv_soft/k8s_08:orders-service
