# creepinggame
问题：
有一根300厘米的细木杆，在第30 厘米、80 厘米、110 厘米、160 厘米、250厘米这五个位置上各有一只蚂蚁。木杆很细，不能同时通过两只蚂蚁。开始时，蚂蚁的头朝左还是朝右是任意的，它们只会朝前走或调头，但不会后退。当任意两只蚂蚁碰头时，两只蚂蚁会同时调头朝相反方向走。假设蚂蚁们每秒钟可以走5 厘米的距离。
请编写一个程序，计算各种可能情形下所有蚂蚁都离开木杆的最小时间和最大时间。
【约束：蚂蚁是有区别的，比如编号、名字】
·Pole类：包含isOverFlow（）一个操作，职责为判断木杆上是否有蚂蚁越界。
·Ant：包含changeDirection（）、creeping（）及getter、setter操作，主要职责为控制蚂蚁的爬行、在碰撞时改变爬行方向。
·PlayRoom：职责为完成游戏的初始化等。
·CreepingGame：包含checkAndHandleCollision（）、checkAntStatus（）、initializeAnts（）等6个操作，职责包括控制游戏的开始、结束以及时间的推进，初始化蚂蚁的状态，检测并处理两只蚂蚁的碰撞等。
