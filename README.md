# example
用来练习的小程序






# SirmCloud

sirm 微服务基础框架

- server: 服务注册中心，基于Eureka来实现的服务注册与调用
- gateway: 服务网关
- producer/consumer: 服务生产者/消费者
- config: 配置中心




## gateway



## producer/consumer

在Spring Cloud中使用Feign, 可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

为了避免服务之间的调用“雪蹦”，采用Hystrix的作为熔断器，避免服务之间的“雪蹦”。