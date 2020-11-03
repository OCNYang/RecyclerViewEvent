# RecyclerViewEvent

## 效果图  

1. swipe and drag.拖曳 + 滑动删除
2. drag. 拖曳 + 首个 item 固定  

![swipe and drag](http://img.shedoor.net/github/recyclerviewevent/recy_swipanddrag.gif?imageView2/2/w/300) ![drag](http://img.shedoor.net/github/recyclerviewevent/recy_drag.gif?imageView2/2/w/300)  

## 讲解

这次主要是把 RecyclerView 比较常用的基本的点，在这里集中整理一下。从这篇文章主要梳理以下几点：

* 优雅的实现：item 点击事件 & item 长点击事件
* RecyclerView 添加 divider 的标准姿势
* RecyclerView 实现 item 的拖曳排序和滑动删除
* 拖曳排序时，限制首个 item 固定的实现

详解介绍请看本项目对应的讲解文章：  
**[RecyclerView 梳理：点击&长按事件、分割线、拖曳排序、滑动删除](https://juejin.im/post/5a320ffcf265da43200342a3)**


## 解决 RecyclerView 复用错乱 之 优雅方式

当 RcyclerView 中存在 CheckBox 或 EditText 时，因为复用机制的存在，会在滚动时造成数据混乱。
虽然网上流传的有多种方法来解决，但都比较繁琐或存在一定缺陷。
这里给大家提供一种比较优雅而合理的方式来解决复用错乱的问题：

![reuse disorder](http://img.shedoor.net/github/recyclervieweventreuse_disorder.gif?imageView2/2/w/300)

方法详情和讲解请看[源码](https://github.com/OCNYang/RecyclerViewEvent/blob/master/app/src/main/java/com/ocnyang/recyclerviewevent/reuse_disorder/EleganceMethodActivity.kt)
