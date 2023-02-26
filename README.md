# java-online-taxi
实现现在如滴滴，神州出行等网约车软件的主要功能的项目，是基于springcloud实现的分布式微服务项目

## 项目搭建

- 使用Maven项目搭建，并且根据pom文件加载相关包
- 安装redis并且在相关微服务项目的application.yml中配置
- 安装nacos并且在相关微服务项目的application.yml中配置
- 安装mysql并且在相关微服务项目的application.yml中配置


服务名|端口号
--- | ---
api-boss|8087
api-driver|8088
api-passenger|8081
service-map|8085
service-driver-user|8086
service-order| 8007
service-passenger-user|8083
service-price|8084
service-verificationcode | 8082
service-sse-push| 9000
test-alipay| 9001



