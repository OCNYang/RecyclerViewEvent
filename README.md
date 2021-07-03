# RecyclerViewEvent

本项目展示内容：  
- [x] 点击事件、长按事件
- [x] 分割线
- [x] 拖曳排序、侧滑删除
- [x] 拖曳排序 & 首个固定
- [x] 复用错乱（复选框、编辑框）
- [x] 定向刷新
- [x] DiffUtil 的使用
- [x] Paging 3 的使用
- [ ] DiffUtil & 定向刷新 结合使用
- [ ] 条目长按的上下文菜单按钮
- [x] ConcatAdapter (旧名：MergeAdapter)

<br/><br/><br/>

## 效果图  

1. swipe and drag.拖曳 + 滑动删除
2. drag. 拖曳 + 首个 item 固定  

![swipe and drag](http://img.shedoor.net/github/recyclerviewevent/recy_swipanddrag.gif?imageView2/2/w/300) ![drag](http://img.shedoor.net/github/recyclerviewevent/recy_drag.gif?imageView2/2/w/300)  

## 1. 基础讲解

这次主要是把 RecyclerView 比较常用的基本的点，在这里集中整理一下。从这篇文章主要梳理以下几点：

* 优雅的实现：item 点击事件 & item 长点击事件
* RecyclerView 添加 divider 的标准姿势
* RecyclerView 实现 item 的拖曳排序和滑动删除
* 拖曳排序时，限制首个 item 固定的实现

详解介绍请看本项目对应的讲解文章：  
**[RecyclerView 梳理：点击&长按事件、分割线、拖曳排序、滑动删除](https://juejin.im/post/5a320ffcf265da43200342a3)**


## 2. 解决 RecyclerView 复用错乱 之 优雅方式

当 RcyclerView 中存在 CheckBox 或 EditText 时，因为复用机制的存在，会在滚动时造成数据混乱。
虽然网上流传的有多种方法来解决，但都比较繁琐或存在一定缺陷。
这里给大家提供一种比较优雅而合理的方式来解决复用错乱的问题：

[![reuse disorder](https://github.com/OCNYang/RecyclerViewEvent/blob/master/docs/recyclervieweventreuse_disorder.gif?raw=true)](http://img.shedoor.net/github/recyclervieweventreuse_disorder.gif)  
[图片显示失败？点击查看](http://img.shedoor.net/github/recyclervieweventreuse_disorder.gif)

方法详情和讲解请看[源码](https://github.com/OCNYang/RecyclerViewEvent/blob/master/app/src/main/java/com/ocnyang/recyclerviewevent/reuse_disorder/EleganceMethodActivity.kt)

## 3. 使用 `notifyItemChanged(int position, @Nullable Object payload)` 定向刷新

具体查看源码说明：[NotifyItemAdapter.onBindViewHolder(holder: NotifyItemViewHolder, position: Int, payloads: MutableList<Any>)](https://github.com/OCNYang/RecyclerViewEvent/blob/master/app/src/main/java/com/ocnyang/recyclerviewevent/notify_item/NotifyItemActivity.kt)

## 4. 使用 DiffUtil 实现局部刷新

具体查看源码说明：[Activity](https://github.com/OCNYang/RecyclerViewEvent/blob/master/app/src/main/java/com/ocnyang/recyclerviewevent/diff_util/DiffUtilActivity.kt)
[AdapterDiffCallback](https://github.com/OCNYang/RecyclerViewEvent/blob/master/app/src/main/java/com/ocnyang/recyclerviewevent/diff_util/AdapterDiffCallback.kt)

## 5. 使用 Paging 3 分页加载

具体查看源码说明：[paging3 相关代码](https://github.com/OCNYang/RecyclerViewEvent/blob/master/app/src/main/java/com/ocnyang/recyclerviewevent/paging3)

## 6. ConcatAdapter 实现多个不同的 Adapter 合并成一个

<img src="https://github.com/OCNYang/RecyclerViewEvent/blob/master/docs/concatadapter.jpg?raw=true" alt="ConcatAdapter" width="300">

使用方式：
```java
    recyclerView.adapter = ConcatAdapter(mHeaderAdapter, mAdapter, mFooterAdapter)
```

* 可以使不同的 Adapter (不同数据、不同布局) 合并成一个 Adapter 设置给 RecyclerView 显示；
* 功能有点类似多 ItemViewType 的 Adapter，不同的是每个 Adapter 是从上到下按顺序显示的，不能混排
* 您可能使用过 `ViewHolder.getAdapterPosition` 来获得 Adapter 中某个 ViewHolder 的位置。现在，因为我们合并了多个 Adapter，作为代替，您需要调用 `ViewHolder.getBindingAdapterPosition ()`。