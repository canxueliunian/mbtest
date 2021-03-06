#mybatis plus 与反射
#vedio录制流程与背板
----
##1.起始--引子
  - hello,各位小伙伴,录这个视频是想要介绍一下mybatis plus 这项技术, 以及结合注解反射使用的效果
  ,我会结合一个小的demo项目来进行演示以及讲解.
  - 因为这也是我第一次录制视频,所有的一切我都尽力,但各个方面也还请多担待.
  - 关于讲解的话, 不深入细节,项目是基于spring boot以及mybatis plus的, 许多的小伙伴对这两个是比较陌生的
    我们更多的讲解分析问题, 以及解决问题的思路,当然关于这两项的背景知识我也会做一定的补足与介绍的.
  好了,下面我们进入正题
##2 项目简介与两个demo演示:
在深入的讲解项目以及mybatis plus这门技术之前, 先演示两个小的操作,看一下实际的使用效果.
看一下使用mybatis plus,能够给我们带来哪些便利.

###1.项目简介:
- 先来简单过一下这个项目,一个比较简洁的sring boot web项目啊,你如果不了解spring boot的话,也不要太在意我们在后面会讲到的,它还是spring的啊
- student表结构, 对应的项目中student模块, bean对象, Controller类, 里面简单的三个方法, 分页查询, 增加对象, 修改对象
- 我们测试一下分页请求, json格式返回, sql查询语句, 中规中矩啊.
*接下来集中注意力,我们看两个神奇的操作*

---
###2.两个demo的演示
- 现在student的表结构是这样的,在日常的开发中我们也是经常会遇到需求变动
需要在原来的表中新增一列的需求
现在往表里新增一列 ever_name varchar(255) 表示曾用名
使用过SSM框架的小伙伴都知道需要对项目做哪些更改的.
在这个项目里只需要做一步
在bean中添加上对应的属性
private String everName;
这就蛮有意思的了啊, 明明是mybatis, 却有些像是对象/关系型思想的hibernate

- 接下来我们看第二个需求变动.目前我的这个分页查询都是等于查询,也就是当你传入参数
sql语句中进行等于拼接
我们测试一个name的.进行请求, sql语句这样的啊,
**现在我想要把这个name查询从等于换成like模糊查询**
在这个项目里的处理是
只需要在这个属性上加上这个注解@ClumnLike
我们测试一下, 看一下sql语句是否发生了变化.
确实是能正常工作, 我们再来处理更复杂的查询
我相把原来的age 等于查询,替换为区间查询.例如查询 年龄age >=20 and age<=23的数据.
我们先在bean中加入辅助字段. ageMin , ageMax
在这个项目中你只需要添加这些注解,就可以了.不用修改xml语句的啊
我们现在来测试一些, 设置请求条件, 并看一下sql的查询语句, 依然是没错误.
还是蛮有意思的啊
现在我们去看一下student模块的xml文件,
它里面是空的啊, 这也是我在一开始介绍模块的时候不进行展示这个地方的原因.

**有没有我很好奇啊, 又没有怦然心动的感觉,这里面第一个操作是mybatis plus自带特性, 第二个则是在其基础上
加上注解反射解析得来的**
##3.spring boot知识铺垫
---
###1.引子
- 在我们深入项目以及mubatis plus前,先补充下spring boot的知识
因为有一些小白是没接触/不了解spring boot项目的,为了加强带入感, 我们先简单铺垫一下
spring boot的背景知识
---
###2.spring boot:
spring boot与spring的关系是有点青出于蓝而胜于蓝的意思, 它是在spring的基础上,进行简化以及加强,最本质的spring还是相同的啊.
spring boot目前也是越来越多的人在使用, 像是微服务的项目都是清一色的使用spring boot的
我们也根据这边的项目结构.简单的解释一些与spring不同的地方
- application.yml
    他是这个项目的配置文件,就相当于spring项目中application.properties
    这个怎么翻译,例如这一个翻译成properties就是 , spring.datasource.url=......
    yml的特性的话是比较简洁, 结构也比较清晰啊, 还有一个类似的是 gradle之于maven,引入相同的东西, 他是这个样子的,再回到项目本身啊
- xml配置:
    我们可以看到resource目录下是没有xml配置文件的啊, 但这确实是个有spring mvc , mybatis,以及日志等的项目啊在这个项目里,spring boot的配置体系是, 一部分配置它会自动进行配置, 剩余的一部分可以通过这里面的属性来设置,最后还有的话,在spring boot中通常是使用java bean来做配置,而spring更倾向于xml配置文件.当然spring项目的话也是支持java bean来做配置的
- web 项目启动部署:
    我们看一下这里的项目debug配置啊,并不是我们熟悉的tomcat啊,它也不是打war包然后tomcat部署.
    而是打jar包,直接运行的,原理我们不讲.
    spring boot的话都有一个启动类, 可以使用启动类直接运行项目
---
##4.mybatis plus讲解
现在我们回到我们的主题 mybatis plus,关于如何在项目里引入以及初始的配置,我们不讲,照着官方文档来也很容易搭建的啊.
mybatis plus是基于mabatis,在其基础上进行加强与简化,对于开发人员更加的友好啊
下面我们通过对比传统项目与plus的项目, 演示一下,它到底有哪些不同, 又强大在哪个地方.
###1.背景简介:
  我们的数据库里一共有两张表, student,book表, 这是使用mybatis的项目, 这是使用plus的项目, 目前都已经有student表对应的模块了.
  我们现在将book模块加入到项目中,还是先做传统项目
###2.代码生成:
####1.传统项目
  修改generater中的表名,进行生成.为了让它符合我们实际的使用,我们对其进行修改
  - 调整xml文件的位置
  - 修改mapper接口,对其泛型进行补充.这里顺便提一句啊, 我之前用的generater是全部在xml中的,这个版本可能比较高 这个版本是一部分放在mapper接口中一部分了,不过不要在意这些细节.
  - 我们把这个map映射改成自动的, 并删除下面的
  - 我们把插入操作,让它返回主键
  - 我们把delete硬删除改成update的软删除
  - bean当中把他改成符合项目风格的lombok  -- 如果没见过这个的话,简单解释一下啊, 在这里加上这个注解, 你的bean里就不需要写getset方法了

*到这里的话,mapper层我们已经修改好了, 但实际上很多情况下这个名字都是要改成符合项目风格的, 例如selectByPrimtkey要改成findById的,我们也不再做处理了.
接下来我们处理service层*

- 复制service接口,修改对象
- 复制impl类, 修改对象, 注入mapper
现在我们的service层也做了,我们来做controller层
- 复制controller层, 修改总的mapping,注入service.修改出来一个查询的mapping
上面的过程就是传统项目里的新增模块的过程.
####2.plus项目
  传统的代码生成是使用maven插件啊,plus中是使用java代码生成啊,具体的配置我们也不解析了, 生成book模块只需要把这里的配置修改表名即可.
  运行代码生成.
  - xml文件有, mapper接口有,并且它是支持软删除的, 也是插入时默认返回主键的, 不需要做额外的操作
  - service层, 两个文件都有啊,我们看一下这里的impl,我们可以注意到, 普通项目的这里的泛型参数是bean对象和对应的主键的类型, mapper通过basemapper 的形式来进行注入
  而在plus中是把mapper 作为参数, 并将其对应的mapper 直接注入到impl中的
  - controller 层, 也帮我们新建好了, 并且设置了总的mapping.
  ---
  结论一: plus的代码生成更加强大, 生成的文件更加完善.

  ---
###3.自带方法:
####1.传统项目
  在看完生成的代码后, 我们再来看一下生成后可以用的方法, 传统项目的话,方法比较有限啊, 也太基础, 其中最需要的全属性查询也没有的啊.
####2.plus项目:
  因为service层我们也是直接用它的,所以我们直接看service层,它要更加直观一些.先看一下它的方法数量,很壮观啊,我数过了一共三十个,排除前两个,是28个方法
  我们再来细致的看一下它给我们提供了哪些功能.
  - save 插入, saveBatch--批量插入, 这个的xml写起来还是有些麻烦的啊, 对于小白也是容易出错的.批量插入的重载,插入/修改, 批量, 批量的重载  一共六个
  - remove 根据id ,根据map条件, 根据wrapper--这个东西叫条件构造器啊, 你可能接触过也没有这样的概念, 后面我们会进行详细的解释, 现在你只需要知道它是一个用来存储查询条件的对象即可.批量根据id删除 **删除方法一共四个**
  - update 根据id修改对象 ,这个是根据查询条件修改对象,批量修改对象 **修改方法一共三个**
  - query  根据id , 根据id的集合返回list,根据map返回list,根据wraper返回一个,根据wrapper返回map结构,返回object结构-- 它这里面的经典重载是, 查询条件重载- wrapper, map,返回重载是map结构, object, t泛型.
  - count 统计
  - page 分页查询返回对象, 返回map
  这些都是可以直接使用的啊
  ---
  结论二: plus自带的方法完全碾压原生的, 并且是完全满足日常的基本使用的

  ---

###4.plus 运行基础原理:
按照正常流程的话, 下面我们应该讲解条件构造器, 但是我知道许多人对于xml是空的, 但是这些方法确可以直接使用耿耿于怀
这里我们也是解释一下.
如果你接触过hibernbate或者mongodb的话就了解这种对象关系型的,他们也是没有sql直接运行的啊
不过不了解也没关系啊.这些东西都是表面的
java 跟数据库交互的本质就是发送sql语句, string的,获取结果进行解析.
就是你一开始学习的jdbctemplate的那一套,mybatis是把sql语句存储在xml当中的,通过id与mapper接口绑定.而在plus当中sql语句也是有的, 只不过是它自动生成, 并且存在其他的地方.
因为涉及到具体的原理,我们也不细讲,至于如何生成,解析bean,
通过bean你可以知道它表名,以及列名, 有这些sql语句随便写就行了,同时这也要求表与bean完整对应, 如果在bean中新增辅助属性的话需要用注解标识出来它不是表的列
也就是我一开始演示的时候加的那个tablefiled注解啊
###5.条件构造器:
上面我们已经讲到解析javabean来生成sql语句了,条件构造器也是基于它的, 不管你之前见没见过,我们先用它来写一个小的demo
- wrapper demo:
  我们需要查询的条件是这样的,book_no为大于4的,同时类型是3,author中带马字的,时间区间在这之内的
   还是有点复杂的.我们一步步来
  - 新建对象,并指定泛型
  - 等于条件这么写
  - 模糊查询这么写
  - 大于等于, 小于等于   -- 方法就是数据库的操作啊

  ---
  代码演示, 演示sql语句

  ---
  再补充一下两个关键的知识点
  - 它有两个常用的构造方法, 传入bean或者是map结构,它包含的所有信息会按照eq来拼接
  - 另一点要强调的是, 虽然它看起来特别像是map的键值对的结构, 但不同的是它没有remove,也就是 这行语句最终的sql是 name= and name like, 它没有删除也没有替换,这一点很重要.

我们可以看到它都遵循这样的操作, 操作类型, 处理的列名称, 要拼接的值.
- 解析queryWrapper:
  我们简单看一下,他所包含的方法, 数据库单表操作全部都含有, 并且可以设置查询的列
  ,并且有终极的直接拼接sql语句的大招啊
- 存在的意义:
  这些方法存在的意义是什么, 是大多数的单表需求都可以通过java代码来解决,而不是xml, 这意味着, 当来了一个复杂的, 没有满足现有条件接口的新需求, 你可以直接在controller中完成需求, 而不是service新建一个接口, impl写个实现, mapper新建个接口, xml中写sql代码.
  java代码其实要比xml要友好一些的啊.
  它的存在让整个项目变得极为的灵活.
##5.mybatis plus 与反射注解
  铺垫完了前面的部分,在知道可以通过wrapper来拼接条件之后,我们来讲解前面为什么我进行复杂的查询时也只需要添加注解就可以了.
  当然我们看项目的话肯定是这个dealData类处理的, 我应该打开这个类, 讲反射,注解解析.
  这样子没多大意义啊,程序也好,生活的很多事情,重要的有价值的都不是这样简单的重复,照着造一个轮子, 而是更深的知道,为什么造这个轮子, 在这种情况下轮子应该如何设计的过程.
  不是说这个地方多么的厉害, 只是说我觉得应该从前到后分析整个完整的过程.
  现在顺着我的思路来进行分析问题解决问题
###1. 简单查询:
  getPage是分页查询的方法, 我们第一版的需求是简单查询,所有的查询都是等于.
  我们用构造方法,构造对象就行了, 这样完全满足需求啊.
###2. like查询:
  过了一阵子,需求改了,这个那么我不要等于查询,我要like查询.

  改吧,我在后面直接拼个like可以吗,不行的啊, 不过还是演示一下效果
  所以我们应该在构建方法之前,获取,单独保存name,设置为null
  在生成的wrapper来进行like , 显示先非空判断.
  做好了这一个,我们来修改book模块.
###3. 大于等于, 小于等于查询
  又过了一阵子,需求又改了,说日期我想区间查询,我们再来处理, 我们先新增两个辅助字段,注解标注它不是表数据字段,
  我们依旧照照着之前,取数据, 设置为空, 处理数据.
###4.结论:
  你们有没有觉得这么做有问题啊,我们虽然不直接写sql语句了,但这些个java代码也是很麻烦的,
  虽说麻烦但太类似了啊,问题在于相同的代码,处理方法,根据个体的差异不断的重复,我们来分析一下问题,并试着解决
  我们最终处理的只有两个对象wrapper和包含查询条件的对象.并且我们需要这个方法可以处理所有的对象,无视具体类型的差异.这是很重要的一点啊
  在java中能实现这种灵活风骚操作的就是反射啊.
  并且mybatis plus 本身哪些sql语句的生成也是基于反射的啊.
  反射是java中学起来有些难,用起来也挺麻烦的东西啊.我们一起试着用反射来处理一下wrapper的封装啊
####1. 简单封装
先从最简单的开始,bean里面的所有值我都当做等于查询用eq拼接
- 先获取他的Class对象
- 获取它所有的属性,进行for循环
- 获取这个传入对象上的这个属性值,如果无则continue进行下一次循环
- 有的话我们进行处理,wrapper.eq("","");这里后面的我们已经有了,我们需要前面的列名啊, 列名的话其实是属性名称的驼峰规则转换,这里我们也生成一下
- 现在我们的一个普通查询就完成了, 我们测试一下.
报错了,我们看一下报错信息,此时应该有一段标准的美式口音读出这些报错信息啊,很可以我是英语课上被老师特别关怀的优等生
他这里说这个版本号属性当做数据库的列了.
如何处理那,我们在这里过滤一下这个属性, 这个完全是可行的, 但带来的另一个问题是我们还有些辅助字段那, 辅助字段也会产生错误的啊.
mybatis plus 本身体系不出错是因为它根据这个注解进行处理了.所以我们来处理一下这个问题
- 注意一下这个注解啊, 表示列存不存在只是它的一个功能, 它还有其他的功能,所以我们最终过滤的数据是, 含有这个注解, 并且他的这个值为false的情况下.
- 然后处理if的部分, 先判断它是否有这个注解, 有的话,判断它是否含有这个属性, 再判断它是否为false, 是的我们删除,
到现在为止我们就完全处理好了,把version字段上补充上注解.

###2. 复杂封装
  在此基础上我们进行更复杂的条件的处理like模糊查询,
  你看这个地方我们like处理是因为你是知道的name字段是要用模糊查询的,但程序是不知道啊,
  怎么办, 你小声的告诉程序
  你已经是个成熟的程序了, 应该知道哪些东西该模糊查询,哪些该大于等于,做代码最重要的是什么, 是有灵性, 只有有灵性,才能....
  你指望着它自己动是不可能的啊, 还是要自己来做的.
  怎么告诉啊,就像前面你告诉这段代码,有tablefiled 的列是不能动的,程序就知道了
  要使用注解, 自定义注解.话说注解这个东西就相当于一个标志, 这个东西从来都是和反射绑定起来进行使用的
  是 注解,我在程序里加了注解.
  我们来处理模糊查询啊.
  - 新建注解
  - 加到对应的属性上
  - 判断属性上是否有该注解, 如果有的话, 进行like的拼接
  - 在完成这一切后一定要continue 跳出本次循环,在也是我需要将它放在普通字段处理前面的原因, 如果不手动跳出的话, 我们有不是if的判断结构, 这个内容是要处理两遍的.
###3.其他复杂处理
  在此基础上,你可以进行设置其他注解, 以及进行处理啊, 我也就不带着你们来写了, 说明一下啊, 关于处理大于小于的时候, 属性的名称并不是数据库的字段的,驼峰规则也转换不出来, 这个地方的话需要设置一个有属性的注解, 手动的在注解上标明其要处理的列.
##6.结语:
  - 到现在为止,我们整个项目就讲解完成了.关于讲的质量的话,一般,毕竟我不能对自己下太狠的手,希望你能有所收获啊
  - 怎么看待mybatis plus 这项技术那,值得你付出时间与精力去学习的
  - 再补充一些其他的内容给注意力跑偏的同学啊,
    - 这个桌面壁纸存在这里, 同时还有这些附带的, 来自p站
    - 背景音乐来自这张歌单啊, 喜欢音乐的小伙伴可以看下这几张歌单啊, 会被惊艳到的
    - 这个软件叫做wox,可以快速打开程序,进行搜索, 程序员的话要有一个的啊
    - 这个是markdown, 就是编程式的同时设定好格式.
  - 那么视频到此为止,再次感谢观看的小伙伴,那么以后再见.之后的话,更方面会更好一些的.


















  ---
接下来我们测试mybatis plus的代码生成.
mybatis的generator都是使用maven插件解析xml配置文件来声称的, plus的generator的话是运行java代码来声称的,
这是我自己写的生成工具, 读取外部的配置文件来声称的.这些具体的详细配置现在也不需要了解, 生成book模块的代码,我们
只需要修改这里的表名, 我们运行一下, 看一下生成的文件, mapper有,
service层也有, controller也有的, 实质上他连前段的模板也是可以生成的.
我们在这个的基础上写一个分页查询.

写好了, 我们测试一下.
可以的啊.
这是mybatis plus 做的很好的一个地方, 它的代码生成要更加的强大

在这个生成的代码的基础上我们再来看一下.他们提供给我们的方法, mybatis 有这几个基础的啊, 她没有我们最常用的全属性查询
并且它的命名, 如果你不用mapper接口转换一下的话,其实是有点不符合我们使用习惯的


讲完了这些的话, 应该是有小伙伴对mapper. xml中没有任何sql代码比较耿耿于怀啊.
这里解释一下, 不光是plus 没有的, ha , 以及mongodb的话也是可以没有的啊.
他没有是怎么实现功能的那, 这需要从源头来将起啊
我们学习java 连接mysql最开始学习的就是jdbcTemplate, 它是怎么使用的啊, 加载驱动, 创建连接, 向数据库发送sql语句,获取查询结果, 进行处理
虽然mybatis在许多地方与她不同, 但最本质都是向mysql发送查询语句,你也可以把她看场是是string的sql语句, 获取查询结果.
只不过mybatis是把它存储在xml当中的, 你看的到,
mybatis plus的话也是有的啊, 只不过是存在其他地方, 我们不讲解底层的原理, 只给你展示一下她确实是有的啊, 在程序启动的时候, 以及这个对象里面.

至于这些代码如何生成那, 依靠解析javabean来声称, 这也就是, 为什么当我们新增一个字段的时候, 不需要对xml进行任何维护的原因, 她的查询, 新猪呢个和修改都是可以正常使用的,

讲到这里已经讲嘞这么久了, 我们休息一下, 之后回来讲解,我们前面看到的queryWrapper.
---
条件构造器:
条件构造器我们再前面提到了是一个可以设置查询条件的查询对象,
例如我们现在查询这样一个东西:
以书为例子:
条件是, 书名中包含了, "字", 然后type为3或者是1的, 出版时间在之后的.
我们加上辅助字段来查询一下试一下
在传统的mybatis中, 我们要新建service接口, impl接口, mapper接口, 然后是xml语句, 其中需要使用大量
的xml格式的mybatis 语法, 写mapper真的很考验个人的细心与水平的, 经常出现错字符的情况啊.
我们

---
这是传统的,

接着我们用条件构造器来写一个
等于是这么写的,
大于这么写
like这么写
or 方法这么写
可以看出它的规律啊,
都是操作, 操作的列, 填充的值.
我们再补充两个关键的, 在之后我们会用到的啊.
两个构造方法, 一个是接受对象作为参数
还有一个重点啊,
就是不管你用那种进行构造了, 进去就是进去了, 它不是map能够去除啊.
例如我这样子写, 我们看的查询就是先等于, 后like--- 这一点很重要, 我们记下来
第二个是接受map结构作为参数,
他们都是取出所有的属性进行eq操作啊.
现在我们来看一下条件构造器的方法, ,可以说所有的单表查询操作 , 你都可以完成的, 设置查询列
足够丰富啊, 并且有boolean的重载方法:

---
- 记住这些东西,我们开始讲解最后的与注解的结合.
为了便于理解我们还是带入一个情景啊.
我们先把book的代码考到student中, 我们之后的代码都不使用xml, 而是用querywapper来代替啊
我们最初的代码就是这样的全部是等于, 简洁 , 中规中矩, 没什么好说的啊
然后需求变动了, 要求name是要模糊查询的,
现有的代码是不能在后面这样进行拼接的.
我们可以这么处理啊, 在构造之前把name取出来, 设置为null, 再此之后再进行设置
// 或者说我们可以用map的形式构建啊,
把对象转换成map, 删除元素再进行构建,
我们把book的也改成这样
---
第二版的需求是 要加入大于等于, 小于等于的查询.
我们先加辅助字段,

- 你们有没有觉得那里不对啊,
我们好像一直在做重复的工作啊, 如果东西多的话, 会累个半死的啊.
我们来重新分析下整个解析过程,
拿到这个对象, 我们把它这几个属性作为eq插入, name属性作为like插入,
这些是普通处理的, 这些是特殊处理的
我们从始至终都是在处理queryWraaper以及这两个对象, 那我们是否能写一个方法, 传入这两个对象, 自动解析条件并设置到wapper
中那
它需要对所有对象生效.
在这种场景下, 我们需要用到反射啊, 已经接触到SSM框架的话, 即使不能熟练的使用反射的话, 也是了解这个东西,
现在我们简化问题啊, 用反射的方法来实现这个wrapper的设置,
wrapper中需要填入的就是两个一个是列的值, 第二个是它的值,
对象如何转化那, 通过反射我们是可以获取到属性的名称的,当然这个地方我们需要使用驼峰规则转化成数据库的列, 以及对应的在bean中的值的.
我们进行代码的书写啊
---
步骤:
- 获取类对象
- 获取当前对象的所有列  **进行for循环**
- 取出当前对象的值, 如果为null , continue
- 不为null , 驼峰规则处理当前属性名称, 并wrapper.eq();
---
通过上述的步骤, 我们对于普通列就处理好了.
那么我们来处理特殊的列:
like 操作,
程序是不知道当遇到这个name字段要解析成like , 而不是eq操作的, 所以你要明确的告诉程序你想要让他这么处理的

怎么告诉那, java程序里的标记处理, 注解,
注解这个东西, 小白的话基本是不能很好的使用自定义注解以及反射处理的, 基本上注解和反射都是绑定起来用的.

**todo** 在前面的过程中会出现version字段出现错误, 那个地方先进行简单处理, 单个过滤, 之后在后面补足的时候
讲@TableFiled(exit=false)的地方进行注解解析来处理



我们自定义一个like注解, 为这个.
然后将它加在name属性上.进行解析步骤的处理:
反射是可以获取到filed上的注解的全部信息的.
我们获取它上面的注解, 如果它是like的话, 我们进行like处理.
这个地方应该写在eq操作的后面, 并在完成操作后, 直接continue跳出本次循环.

---
接着我们做一个大于等于的注解.
大于等于使用的字段都是辅助字段, 它的名称通过驼峰解析也不是正确的数据库表的列名, 所有我们对其进行设置属性,
并且在解析的时候设置列名时, 获取注解中的值, 而非解析属性名称.
完成解析后, 依然是通过跳continue来跳出本次循环

---
对于小于等于的话也是如此啊.


---
至此我们完成了mybatis plus + 反射注解解析的过程.
这也是我们在一开始, 当查询条件发生变化, 并且变得复杂的时候只需要通过添加注解即可.

当然注反射的形式, 有这一些判断是有性能损失的, 但其实也损失不了多少, 但是这么处理的 话, 对于开发的话则是足够的便捷, 以及
带来很好的后期维护.
