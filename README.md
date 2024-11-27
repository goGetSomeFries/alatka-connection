### alatka-connection是什么

基于Spring Integration实现，高度可配置化（Yaml）的企业集成模式框架。

> [Spring Integration](https://spring.io/projects/spring-integration) 是一个功能强大的企业集成模式（[Enterprise Integration Patterns](http://www.eaipatterns.com)，EIP）框架，它是Spring框架的一个扩展，旨在简化企业集成模式的开发。
它提供了消息传递的抽象和一系列组件，支持多种传输协议和消息中间件。Spring Integration通过消息传递的方式实现了系统间的松耦合和异步通信，特别适用于需要多个系统或功能之间进行消息交互的场景。

### 主体流程

整体分为`inbound`**入站**、`outbound`**出站**、`processor`**处理器**三个部分；
`inbound`对接上游系统，接收上游数据，流转多个`processor`进行数据转换处理，通过`outbound`转发下游系统并得到响应；再反向经过`processor`处理，最终回传给上游系统，完成一次数据交互。

整体流程全部通过`yaml`文件配置实现，只需实现核心业务逻辑，并提供丰富的组件，极大简化代码开发。

![输入图片说明](https://foruda.gitee.com/images/1732331639122176984/d37045c1_2152177.png "alatka-connection流程概述.drawio.png")

### 功能概述

- 支持各类网络协议对接转换
    - TCP/UDP
    - HTTP
    - RabbitMQ/AMQP
    - WebServices
    - Websocket/STOMP
    - JMS
    - JMX
    - MQTT
- 开箱即用功能
    - 流量控制
    - 权限控制
    - Jdbc
    - redis
- 高度配置化
    - 基于Yaml文件配置
    - 专注业务场景实现
- 高扩展性
- TODO

### 使用场景

- 交易前置系统
- 协议适配类系统
- MQ消息转接系统

### [访问wiki查看更多教程](https://gitee.com/asuka2001/alatka-connection/wikis)

### github地址

项目同步更新在github；如有需要，[点击我访问](https://github.com/goGetSomeFries/alatka-connection)

### 感谢支持

如果觉得好用，欢迎推荐给身边同事同学朋友；也欢迎各位的issues和star，问题会及时回复，再次感谢大家的支持！