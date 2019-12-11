## Values_utils

提供Android国际化翻译多values/strings.xml、values/arrays.xml与Excel文件之间互相转换


**开源不易，如果喜欢的话希望给个 `Star` 或 `Fork` ^_^ ，谢谢**


## 前言
开发完成后已经应用进项目，基本可以实现功能，考虑后期支持指定默认以及导出规则


## 简介
[Values_utils](https://github.com/Felix1030/values_utils) 采用 `Java` 语言编写的项目，项目代码结构清晰并且有详细注释，如有任何疑问和建议请提 [Issues](https://github.com/Felix1030/values_utils/issues) 或联系 qq： **675579354** ，**项目会持续迭代维护，努力打造一款好用的插件**。

#### 一、功能介绍
1. **支持多strings文件导出到Excel文件**
2. **支持多arrays文件导出到Excel文件(目前仅支持单个strings-arrays导出)**
3. **支持Excel文件导入多strings文件**
4. **支持Excel文件导入多arrays文件(目前仅支持单个strings-arrays导入)**

#### 二、应用
1. **Android国际化strings翻译**
1. **Android国际化arrays 错误码翻译**

#### 三、功能介绍

#### 四、Q&A
1. "Program type already present: org.apache.xmlbeans.xml.stream.Location"
    这个是由于easyexcel 引用org.apache.poi包下引用xmlbeans-2.6.0.jar导致的冲突
    解决方式使用如下引用即可
``` groovy
implementation ('com.felix.values.utils:values_utils:1.0.0') {
   exclude group:'org.apache.poi'
}
```
    
## Maven

```xml
<dependency>
    <groupId>com.felix.values.utils</groupId>
    <artifactId>values_utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Gradle via JCenter

``` groovy
implementation 'com.felix.values.utils:values_utils:1.0.0'
```
``` groovy
implementation ('com.felix.values.utils:values_utils:1.0.0') {
   exclude group:'org.apache.poi'
}
```

## Thanks

**感谢所有优秀的开源项目 ^_^** 。

## Statement
**项目中的 Excel读取以及写入引用阿里巴巴开源库 [https://github.com/alibaba/easyexcel](https://github.com/alibaba/easyexcel) 。**

## LICENSE
