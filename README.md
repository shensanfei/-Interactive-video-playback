# -Interactive-video-playback
## 类的功能与描述

### BackGroundPanel（未使用）

* 变量
  1. Image BackIcon：背景图片

* 方法
  1. 含参构造： BackGroundPanel(Image BackIcon)
  2. 绘制图片：paintComponent(Graphics g)

### MyTreeNode

* 变量
  1. String mystring：视频地址
  2. textSetting TextSet：选项框
  3. boolean has_action：是否添加行为

* 方法
  1. 含参构造：MyTreeNode(String title)

###  MyVideoFrame

* 变量
  1. MyTreeNode now_play_TreeNode：当前节点

* 方法
  1. 含参构造：MyVideoFrame(String title,MyTreeNode node)
  2. 播放当前对应视频：play()

### PreWinDemo（main所在方法，无需调用）

### ScreenUtils

* 方法
  1. getScreenWidth()：返回当前屏幕宽度
  2. getScreenHeight()：返回当前屏幕高度

### textSetting

* 变量
  1. 
* 方法
