# JSPHorse

![](https://img.shields.io/badge/build-passing-brightgreen)
![](https://img.shields.io/badge/JavaParser-3.23.1-blue)
![](https://img.shields.io/badge/Java-8-red)

## 简介

一个JSP免杀Webshell生成器，目前仅支持普通回显Webshell，后续可能支持冰蝎等，核心技术为Java的AST库JavaParser

特点：

- Java反射调用
- 双重异或加密数字常量
- 凯撒密码随机偏移并结合Base64双重加密字符串变量
- 使用控制流平坦化并随机生成分发器
- 所有标识符全部替换为随机字符串
- 支持全局Unicode编码
- 每次执行都会生成完全不同的马
- 集成木头神提供的ScriptEngine调用JS免杀（未测试）

简单测试了免杀效果：百度WEBDIR+; SHELLPUB; WINDOWS DEFENDER

## Quick Start

生成标准形式Webshell

`java -jar JSPHorse.jar -p your_password`

全局Unicode编码（JSP支持全局Unicode编码）

`java -jar JSPHorse.jar -p your_password -u`

使用木头神提供的JS免杀（未测试）

`java -jar JSPHorse.jar -p your_password -j`

## 免责申明

未经授权许可使用`JSPHorse`攻击目标是非法的

本程序应仅用于授权的安全测试与研究目的


