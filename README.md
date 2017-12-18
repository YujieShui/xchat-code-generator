# xchat-code-generator

为了简少工作中的重复工作，编写程序自动生成一些重复的代码。

实现两个功能：

1. 读取数据库中的表，生成需要的文件。

2. 读取 Excel 中的文件，将 Excel 中的信息导入数据库

# 读取数据库中的表，生成需要的文件

使用 freemarker 自定义模板

```xml

<!-- freemarker -->
<dependency>
  <groupId>org.freemarker</groupId>
  <artifactId>freemarker</artifactId>
  <version>2.3.20</version>
</dependency>      
```
重复的代码包括 SSM 的常规代码以及 ProtoBuf 文件，所以配置了 vo,interface,service,mapper.xml 的模板，以及 Proto 的模板并编译它。

Controller 还没有写，因为 Controller 变化比较多。

# 读取 Excel 中的文件，将 Excel 中的信息导入数据库

这个是以前从哪里拷贝来的，也没去找原来项目地址。

能将 Excel 中配置的信息导入到数据库，但是限制比较多。比如说不能设置主键自增，只支持 .xls 等。
