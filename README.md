## Values_utils

提供Android国际化翻译多values/strings.xml、values/arrays.xml与Excel文件之间互相转换


**开源不易，如果喜欢的话希望给个 `Star` 或 `Fork` ^_^ ，谢谢**


## 前言
开发完成后已经应用进项目，基本可以实现功能，考虑后期支持指定默认以及导出规则


## 简介
[Values_utils](https://github.com/Felix1030/values_utils) 采用 `Java` 语言编写的项目，项目代码结构清晰并且有详细注释，如有任何疑问和建议请提 [Issues](https://github.com/Felix1030/values_utils/issues) 或联系 qq： **675579354** ，[CSDN博客地址](https://blog.csdn.net/hxy_blog)**项目会持续迭代维护，努力打造一款好用的插件**。

## Maven

```xml
<dependency>
    <groupId>com.felix.values.utils</groupId>
    <artifactId>values_utils</artifactId>
    <version>1.0.5</version>
</dependency>
```

## Gradle via JCenter

``` groovy
implementation 'com.felix.values.utils:values_utils:1.0.5'
```

#### 一、功能介绍
1. **支持多strings文件导出到Excel文件**
2. **支持多arrays文件导出到Excel文件(目前仅支持单个strings-arrays导出)**
3. **支持Excel文件导入多strings文件**
4. **支持Excel文件导入多arrays文件(目前仅支持单个strings-arrays导入)**

#### 二、应用
1. **Android国际化strings翻译**
1. **Android国际化arrays 错误码翻译**

#### 三、使用

##### 3.1 ValueState

```
ValueState.STRINGS_TO_EXCEL 导出Strings到Excel
ValueState.EXCEL_TO_STRINGS 导入excel到strings
ValueState.ARRAYS_TO_EXCEL 导出Arrays到Excel 需要指定setArraysXmlKey
ValueState.EXCEL_TO_ARRAYS 导入Excel到Arrays 需要指定setArraysXmlKey
ValueState.NONE 啥也不干
```

##### 3.2 kotlin
``` kotlin
fun main() {
    ValueUtilsManagerBuilder(ValueState.STRINGS_TO_EXCEL)
        .setImportAndExportPath("/Users/vincent/Desktop/workspace/Clock/resource/src/main/res") // 指定导出或者导入的路径
        .setArraysXmlKey("clock_error_codes") // 指定arrays的Key 暂时不支持多Key
        .build()
        .execute()
}
```

##### 3.3 java
``` java
public class GenerateJava {
    public static void main(String[] args) {
        new ValueUtilsManager.ValueUtilsManagerBuilder(ValueState.STRINGS_TO_EXCEL)
                .setImportAndExportPath("/Users/vincent/Desktop/workspace/Clock/resource/src/main/res")
                .setArraysXmlKey("clock_error_codes")
                .build()
                .execute();
    }
}
```


## Thanks

**感谢所有优秀的开源项目 ^_^** 。

## Statement
**项目中的 Excel读取以及写入引用阿里巴巴开源库 [https://github.com/alibaba/easyexcel](https://github.com/alibaba/easyexcel) 。**

## LICENSE
