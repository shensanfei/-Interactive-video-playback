package n;

import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;
import java.awt.*;

//选项跳转
public class textSetting extends JFrame{
	public String[] text = new String[10];
	public JFrame User_frame;
	public JFrame frame;
	
	//与播放的JFrame耦合
	public MyVideoFrame VideoFrame;
	
	
	public MyTreeNode Node;
	public int my_choice=-1;
	
	//主窗体
	Box is_contentPane=Box.createVerticalBox(); 
	JPanel choicePane=new JPanel();
	textSetting() {
		frame = new JFrame();
		frame.setSize(1200, 700);
	}
		
	//在需要的时候显示窗口，isUer为是否是用户，txtNum为对应Node的子节点数
	public void show_it(boolean isUser,int textNum) {	
		Box contentPane=Box.createVerticalBox(); 
		contentPane.setBorder(BorderFactory.createEtchedBorder());
 	 	contentPane.setSize(new Dimension(120,30));

 	 	//是用户，在播放页面显示按钮
 	 	if(isUser) {
 	 		if(textNum==0) {
 	 			choicePane.removeAll();
	 			is_contentPane.setBorder(BorderFactory.createEtchedBorder());
	 	 	 	is_contentPane.setSize(new Dimension(120,30));
	 	 	 	
				choicePane.add(new JLabel("视频播放完毕，请关闭窗口"));
				choicePane.revalidate();
				is_contentPane.add(choicePane);
				
				//在播放的Frame上显示按钮
				VideoFrame.add(is_contentPane,BorderLayout.SOUTH);
				is_contentPane.revalidate();
				VideoFrame.revalidate();
 	 		}
 	 		else {
	 	 		choicePane.removeAll();
	 			is_contentPane.setBorder(BorderFactory.createEtchedBorder());
	 	 	 	is_contentPane.setSize(new Dimension(120,30));
	 	 	 	
	 	 	 	
				choicePanel choice_panel=new choicePanel(textNum,text);
				choicePane.add(choice_panel.panel);
				choicePane.revalidate();
				is_contentPane.add(choicePane);
				
				//在播放的Frame上显示按钮
				VideoFrame.add(is_contentPane,BorderLayout.SOUTH);
				is_contentPane.revalidate();
				VideoFrame.revalidate();
 	 		}
		}
 	 	//不是用户，显示设置窗体
 	 	else {
 	 		
 	 		choicePane.removeAll();
			frame.setTitle("请设置选择");
			choiceSetPanel choice_set_panel=new choiceSetPanel(textNum,text);
			choicePane.add(choice_set_panel.set_panel);
			choicePane.revalidate();
 	 		//组装选择界面
			contentPane.add(Box.createVerticalGlue()); 
			contentPane.add(choicePane);
			contentPane.add(Box.createVerticalGlue()); 
			frame.add(contentPane);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			contentPane.revalidate();
			frame.revalidate();
			frame.setVisible(true);
	
 	 	}
 	 	
 	 	
 	 	
	}


//用户选择选项panel
	class choicePanel{
		JPanel panel=new JPanel();
		choicePanel(int num,String[] text){
			panel.setLayout(new FlowLayout());
			ButtonGroup group = new ButtonGroup();
	//		JRadioButton[] button=new JRadioButton[num];
	//		for(int i=0;i<num;i++) {
	//			button[i]=new JRadioButton(text[i],false);
	//			group.add(button[i]);
	//			panel.add(button[i]);
	//		}
	//		JButton btn = new JButton("确定");
	//    btn.setFont(new Font(null, Font.PLAIN, 15));
	//    btn.addActionListener(new ActionListener() {
	//        public void actionPerformed(ActionEvent e) {
	//        	for(int i=0;i<num;i++) {
	//        		if(button[i].isSelected()) {
	//        			my_choice=i;
	//        			
	//        			break;
	//        			
	//        		}
	//        	}
	        JButton[] button=new JButton[num];
	    	for(int i=0;i<num;i++) {
	    		button[i]=new JButton(text[i]);
	    		group.add(button[i]);
	    		panel.add(button[i]);
	    	}
	    		
	    	for(int i=0;i<num;i++) {
	    		//给每个按钮添加监听器
	    		button[i].addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				for(int i=0;i<num;i++) {
	    					my_choice = i;	
	    					VideoFrame.remove(is_contentPane);
	    					VideoFrame.revalidate();
	    					//通过参数，实现在选择后播放视频
	    					VideoFrame.now_play_TreeNode = (MyTreeNode)VideoFrame.now_play_TreeNode.getChildAt(my_choice);
	    					VideoFrame.play();
	            		}
	    			}
	    		});
	    	}
		}
	}
	
	//创建者设置选项panel
	class choiceSetPanel{
		JPanel set_panel=new JPanel();
		choiceSetPanel(int num,String[] text){
			set_panel.setLayout(new GridLayout(num+1,1));
			//实现编辑选择
			JPanel[] panel=new JPanel[num];
			JTextField[] textfield=new JTextField[num];
			for(int i=0;i<num;i++) {
				panel[i]=new JPanel();
				panel[i].add(new JLabel("第"+(i+1)+"种选择："));
				textfield[i]=new JTextField(10);
				panel[i].add(textfield[i]);
				set_panel.add(panel[i]);
			}
			JButton btn = new JButton("提交");
			btn.setFont(new Font(null, Font.PLAIN, 15));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<num;i++) {
						text[i]= textfield[i].getText();
					}
	        	frame.dispose();
				}
			});
	    set_panel.add(btn);
		}
	}

}
