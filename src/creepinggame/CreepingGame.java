package creepinggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;


public class CreepingGame extends JFrame  {
	
	private JFrame gameBoard;//游戏主框架
	private JButton startButton;//启动游戏的按钮
	private static JPanel jp=new JPanel();//游戏主画板
	private JPanel pole=new JPanel();//可视化竹竿
	private static JLabel[] ants = new JLabel[5];//可视化蚂蚁图片
	private JLabel timer=new JLabel();//计时器
	private JLabel[] antsposition=new JLabel[5]; //显示每只蚂蚁运行中的位置
	private JLabel possibilities=new JLabel();
	private JLabel minTime=new JLabel();
	private JLabel maxTime=new JLabel();
    private static ImageIcon[] rightant=new ImageIcon[5];
    private static ImageIcon[] leftant=new ImageIcon[5];
    private JFormattedTextField text;
    private boolean isrunning;
    private Thread thread;
    private  MyRunnable myrunnable; 
    private int mintime;
    private int maxtime;
   
    class MyRunnable implements Runnable{//该游戏的核心，因和CreepingGame联系紧密，故定义为内部类，实现Runnable接口并重写run函数，执行游戏
        private Ant[] ant=new Ant[5];
        private Pole Pole;
    	
        public MyRunnable(Ant[] ant,Pole pole) {
             this.ant=ant;
             this.Pole=pole;
        }

        @Override
        public void run() {
        	 int time=1;
 			while(!gameOver(ant,Pole))
 			{
 		 try {
 				Thread.sleep(1000);
 				timer.setText("时间:"+time+"秒");
 				for(int i=0;i<5;i++)
 				{
 					if(ant[i].getPosition()!=900&&ant[i].getPosition()!=0)
 					antsposition[i].setText("第"+(i+1)+"个蚂蚁的位置："+ant[i].getPosition()/3);
 					else
 					antsposition[i].setText("第"+(i+1)+"个蚂蚁的位置：已掉落");
 				}
 				checkandhandleCollision(ant,Pole);	 	
 			for(int i=0;i<5;i++)
 			{
 				if(!ant[i].getIsOut())
 				{
 				ant[i].creeping();
 				ants[i].setBounds(100+ant[i].getPosition(),340,60,40);
 				jp.add(ants[i]);
 				}
 				else
 					jp.remove(ants[i]);
 			}
 			jp.repaint();
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		      time++;
 			  }
 		    if(time-1>maxtime)
 		    	maxtime=time-1;
 		    	if(time-1<mintime)
 		    	mintime=time-1;
 		    	maxTime.setText("当前最大时间："+maxtime+"秒");
 		    	minTime.setText("当前最小时间："+mintime+"秒");
 		        isrunning=false;
 	   }
    }
  
   
    public void initializeAnts(Ant[] ant)//每次一种可能性结束之后需要初始化蚂蚁的位置
    {
    	ant[0].setPosition(90);
		ant[1].setPosition(240);
		ant[2].setPosition(330);
		ant[3].setPosition(480);
		ant[4].setPosition(750);
		for(int i=0;i<5;i++)
			ant[i].setIsOut(false);
		for(int i=0;i<5;i++)
	         ants[i].setBounds(100+ant[i].getPosition(), 340, 60,40);
	    for(int i=0;i<5;i++) {
			if (ant[i].getDirection() == -1)
				ants[i].setIcon(leftant[i]);
			else
				ants[i].setIcon(rightant[i]);
		}
    }
    
   
    public void initializeAntsPicture()//初始化五只蚂蚁的图片
    {
        for(int i=0;i<5;i++)
        {
        	rightant[i]=new ImageIcon("image/rightant"+(i+1)+".jpg");
        	leftant[i]=new ImageIcon("image/leftant"+(i+1)+".jpg");
            rightant[i].setImage(rightant[i].getImage().getScaledInstance(60, 40,Image.SCALE_DEFAULT ));
            leftant[i].setImage(leftant[i].getImage().getScaledInstance(60, 40,Image.SCALE_DEFAULT ));
        }
    }
    
   
    public CreepingGame(Ant[] ant,Pole Pole,Vector[] possibility) {//构造函数，包含所有控件和蚂蚁图片的初始化以及响应事件
		// TODO Auto-generated constructor stub
	    mintime=1000000;
	    maxtime=0;
	    isrunning=false;
		gameBoard = new JFrame();
		startButton = new JButton("开始游戏");
        text = new JFormattedTextField();
        text.setFont(new Font("宋体", Font.BOLD, 16));
        text.setBounds(100, 150, 100, 30);
        text.setText(null);
        timer.setText("时间:");
        timer.setFont(new Font("宋体", Font.BOLD, 16));
        timer.setBounds(800,100,100,20);
        minTime.setText("当前最小时间：");
        maxTime.setText("当前最大时间：");
        minTime.setFont(new Font("宋体", Font.BOLD, 16));
        maxTime.setFont(new Font("宋体", Font.BOLD, 16));
        minTime.setBounds(800,130,200,20);
        maxTime.setBounds(800,160,200,20);
        possibilities.setText("请输入第几种可能性（一共有32种）：");
        possibilities.setFont(new Font("宋体", Font.BOLD, 16));
        possibilities.setBounds(100, 100,300 ,20 );
        for(int i=0;i<5;i++)
        {
        	ants[i]=new JLabel();
            antsposition[i]=new JLabel();
            antsposition[i].setText("第"+(i+1)+"个蚂蚁的位置：");
            antsposition[i].setFont(new Font("宋体", Font.BOLD, 16));
            antsposition[i].setBounds(500,100+i*20,200,20);
        }
        initializeAntsPicture();
        initializeAnts(ant);
        pole.setLayout(null);
        pole.setBounds(100,400,900,20);
        pole.setBackground(Color.DARK_GRAY);
		startButton.setBounds(400,700,100,30);
		jp.setBackground(Color.white);
		jp.setLayout(null);
		jp.add(startButton);
		jp.add(pole);
		jp.add(timer);
		jp.add(minTime);
		jp.add(maxTime);
		jp.add(possibilities);
		jp.add(text);
		for(int i=0;i<5;i++)
		{
		jp.add(ants[i]);
		jp.add(antsposition[i]);
		}
		gameBoard.setTitle("蚂蚁爬杆");
		gameBoard.getContentPane().add(jp);
	    gameBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameBoard.setSize(1100,1000);
		gameBoard.setLocationRelativeTo(null);
		gameBoard.setVisible(true);
		startButton.addActionListener(new ActionListener()
		{
	   public void actionPerformed(ActionEvent e)
	   {
		   if(isrunning)
		   {
			   JOptionPane.showMessageDialog(null, "游戏正在运行中，请勿点击");  
		       return;
		   }
		   initializeAnts(ant);
		   int i;
		   if(text.getText().equals(""))
		   {
			   JOptionPane.showMessageDialog(null, "请输入第几种可能性");  
		       return;
		   }
		   for (i = 0; i < text.getText().length(); i++)
		   { 
			   if (!Character.isDigit(text.getText().charAt(i)))
			   {  
				   JOptionPane.showMessageDialog(null, "只能输入1-32之间的数字哦！");   
			       text.setText(null);
				   break;
			   }
		   }
		   
		   if(i==text.getText().length())
		   {
			   int num=Integer.parseInt(text.getText())-1;
	           if(num>=0&&num<=31)
	           {
			    for(int j=0;j<5;j++)
		        {
			        ant[j].setDirection(Integer.parseInt(String.valueOf(possibility[num].get(j))));    	
		        	if(ant[j].getDirection()==-1)
		        		ants[j].setIcon(leftant[j]);
		        	else
		        		ants[j].setIcon(rightant[j]);
		        	jp.add(ants[j]);
		        }
			    jp.repaint();
			    for(i=0;i<5;i++)
			    	System.out.println(ant[i].getPosition());
			    startgame(ant,Pole);
	           }
	           else
	           {
	        	   JOptionPane.showMessageDialog(null, "只能输入1-32之间的数字哦！");   
	        	   text.setText(null);
	           }
		   }
	   }
	   });
   }
   
    
    public static boolean isCollision(Ant[] ant,int presentAnt)//判断presentAnt处蚂蚁是否冲突
   {
   	if(presentAnt==0)
   	{
   		if(ant[1].getPosition()==ant[0].getPosition())
   			return true;
   	}
   	else if(presentAnt==4)
   	{
   		if(ant[3].getPosition()==ant[4].getPosition())
   			return true;
   	}
   	else if(presentAnt==2||presentAnt==3||presentAnt==1)
   	{
   		if((ant[presentAnt-1].getPosition()==ant[presentAnt].getPosition())
   		  ||(ant[presentAnt+1].getPosition()==ant[presentAnt].getPosition()))
   		{
   			return true;
   		}
   	}
   	return false;  
   }
   
    
    public static void checkantsstatus(Ant[] ant,Pole pole)//检查蚂蚁的状态，是否掉落竹竿还是继续爬行，若掉落，则设置为isout
    {
	   for(int i=0;i<5;i++)
	   {
		   if(pole.isOverflow(ant[i]))
			   ant[i].setIsOut(true);
	   }
    }
   
    public static void checkandhandleCollision(Ant[] ant,Pole Pole)//检查并且处理冲突，调用checkantsstatus，并且调用isCollision检查每一只蚂蚁是否冲突，若冲突，则改变方向
    {
    	checkantsstatus(ant,Pole);
    	for(int i=0;i<5;i++)
    	{
    		if(!ant[i].getIsOut())
    		{
    		if(isCollision(ant,i))
    		{
    			ant[i].changeDirection();
    			if(ant[i].getDirection()==1)
		        	ants[i].setIcon(rightant[i]);
		        else
		        	ants[i].setIcon(leftant[i]);
		        jp.add(ants[i]);
    		}
    		}
    	}
    }
    
    
    public static boolean gameOver(Ant[] ant,Pole pole)//检查程序是否结束，若所有的蚂蚁都掉落则游戏结束
    {
    	for(int i=0;i<5;i++)
    	{
    		if(!ant[i].getIsOut())
    			return false;
    	}
    	return true;
    }

	
    public void startgame(Ant[] ant,Pole Pole)//游戏的启动函数，调用内部类runnable执行线程
	{
	  isrunning=true;
	  myrunnable=new MyRunnable(ant,Pole);
      thread=new Thread(myrunnable);
      thread.start();
    }
}
