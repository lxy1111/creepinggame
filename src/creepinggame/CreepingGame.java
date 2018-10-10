package creepinggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;


public class CreepingGame extends JFrame  {
	
	private JFrame gameBoard;//��Ϸ�����
	private JButton startButton;//������Ϸ�İ�ť
	private static JPanel jp=new JPanel();//��Ϸ������
	private JPanel pole=new JPanel();//���ӻ����
	private static JLabel[] ants = new JLabel[5];//���ӻ�����ͼƬ
	private JLabel timer=new JLabel();//��ʱ��
	private JLabel[] antsposition=new JLabel[5]; //��ʾÿֻ���������е�λ��
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
   
    class MyRunnable implements Runnable{//����Ϸ�ĺ��ģ����CreepingGame��ϵ���ܣ��ʶ���Ϊ�ڲ��࣬ʵ��Runnable�ӿڲ���дrun������ִ����Ϸ
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
 				timer.setText("ʱ��:"+time+"��");
 				for(int i=0;i<5;i++)
 				{
 					if(ant[i].getPosition()!=900&&ant[i].getPosition()!=0)
 					antsposition[i].setText("��"+(i+1)+"�����ϵ�λ�ã�"+ant[i].getPosition()/3);
 					else
 					antsposition[i].setText("��"+(i+1)+"�����ϵ�λ�ã��ѵ���");
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
 		    	maxTime.setText("��ǰ���ʱ�䣺"+maxtime+"��");
 		    	minTime.setText("��ǰ��Сʱ�䣺"+mintime+"��");
 		        isrunning=false;
 	   }
    }
  
   
    public void initializeAnts(Ant[] ant)//ÿ��һ�ֿ����Խ���֮����Ҫ��ʼ�����ϵ�λ��
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
    
   
    public void initializeAntsPicture()//��ʼ����ֻ���ϵ�ͼƬ
    {
        for(int i=0;i<5;i++)
        {
        	rightant[i]=new ImageIcon("image/rightant"+(i+1)+".jpg");
        	leftant[i]=new ImageIcon("image/leftant"+(i+1)+".jpg");
            rightant[i].setImage(rightant[i].getImage().getScaledInstance(60, 40,Image.SCALE_DEFAULT ));
            leftant[i].setImage(leftant[i].getImage().getScaledInstance(60, 40,Image.SCALE_DEFAULT ));
        }
    }
    
   
    public CreepingGame(Ant[] ant,Pole Pole,Vector[] possibility) {//���캯�����������пؼ�������ͼƬ�ĳ�ʼ���Լ���Ӧ�¼�
		// TODO Auto-generated constructor stub
	    mintime=1000000;
	    maxtime=0;
	    isrunning=false;
		gameBoard = new JFrame();
		startButton = new JButton("��ʼ��Ϸ");
        text = new JFormattedTextField();
        text.setFont(new Font("����", Font.BOLD, 16));
        text.setBounds(100, 150, 100, 30);
        text.setText(null);
        timer.setText("ʱ��:");
        timer.setFont(new Font("����", Font.BOLD, 16));
        timer.setBounds(800,100,100,20);
        minTime.setText("��ǰ��Сʱ�䣺");
        maxTime.setText("��ǰ���ʱ�䣺");
        minTime.setFont(new Font("����", Font.BOLD, 16));
        maxTime.setFont(new Font("����", Font.BOLD, 16));
        minTime.setBounds(800,130,200,20);
        maxTime.setBounds(800,160,200,20);
        possibilities.setText("������ڼ��ֿ����ԣ�һ����32�֣���");
        possibilities.setFont(new Font("����", Font.BOLD, 16));
        possibilities.setBounds(100, 100,300 ,20 );
        for(int i=0;i<5;i++)
        {
        	ants[i]=new JLabel();
            antsposition[i]=new JLabel();
            antsposition[i].setText("��"+(i+1)+"�����ϵ�λ�ã�");
            antsposition[i].setFont(new Font("����", Font.BOLD, 16));
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
		gameBoard.setTitle("��������");
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
			   JOptionPane.showMessageDialog(null, "��Ϸ���������У�������");  
		       return;
		   }
		   initializeAnts(ant);
		   int i;
		   if(text.getText().equals(""))
		   {
			   JOptionPane.showMessageDialog(null, "������ڼ��ֿ�����");  
		       return;
		   }
		   for (i = 0; i < text.getText().length(); i++)
		   { 
			   if (!Character.isDigit(text.getText().charAt(i)))
			   {  
				   JOptionPane.showMessageDialog(null, "ֻ������1-32֮�������Ŷ��");   
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
	        	   JOptionPane.showMessageDialog(null, "ֻ������1-32֮�������Ŷ��");   
	        	   text.setText(null);
	           }
		   }
	   }
	   });
   }
   
    
    public static boolean isCollision(Ant[] ant,int presentAnt)//�ж�presentAnt�������Ƿ��ͻ
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
   
    
    public static void checkantsstatus(Ant[] ant,Pole pole)//������ϵ�״̬���Ƿ������ͻ��Ǽ������У������䣬������Ϊisout
    {
	   for(int i=0;i<5;i++)
	   {
		   if(pole.isOverflow(ant[i]))
			   ant[i].setIsOut(true);
	   }
    }
   
    public static void checkandhandleCollision(Ant[] ant,Pole Pole)//��鲢�Ҵ����ͻ������checkantsstatus�����ҵ���isCollision���ÿһֻ�����Ƿ��ͻ������ͻ����ı䷽��
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
    
    
    public static boolean gameOver(Ant[] ant,Pole pole)//�������Ƿ�����������е����϶���������Ϸ����
    {
    	for(int i=0;i<5;i++)
    	{
    		if(!ant[i].getIsOut())
    			return false;
    	}
    	return true;
    }

	
    public void startgame(Ant[] ant,Pole Pole)//��Ϸ�����������������ڲ���runnableִ���߳�
	{
	  isrunning=true;
	  myrunnable=new MyRunnable(ant,Pole);
      thread=new Thread(myrunnable);
      thread.start();
    }
}
