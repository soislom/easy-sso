# easy-sso

> 基于 shiro+jwt 的单点登录系统



> 什么是单点登录：
>
> ​	简单来说就是一个地方登录，在多个地方共享当前登录信息的系统就叫单点登录



> 单点登录的三种实现方式：
>
> ​	一、父端 Cookie：
>
> ​			利用二级域名可以共享顶级域名的 Cookie来实现，主要是通过 domain和path两个属					性。实现简单，但是不支持跨主域名。
>
> ​	二、认证中心：
>
> ​	三、localStorage 跨域：
>
> ​	

> 生成启动脚本命令： mvn clean package appassembler:assemble

```css
 [ ] 没选中的复选框
 [√] 选中复选框
```