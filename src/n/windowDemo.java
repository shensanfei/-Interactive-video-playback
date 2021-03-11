package n;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.NoRouteToHostException;
import java.util.Enumeration;


import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.media.Media;


//主要剪辑窗口

public class windowDemo {	
	//定义初始节点
    MyTreeNode china = new MyTreeNode("初始节点");
    
    
    //定义窗口属性
    JFrame jf=new JFrame("主要框");
    int WIDTH=ScreenUtils.getScreenWidth();
    int HEIGHT=ScreenUtils.getScreenHeight()-50;


    //定义分割框
    JSplitPane sp=new JSplitPane();

    //定义菜单
    JMenuBar main_menu=new JMenuBar();
    JMenu file=new JMenu("文件");
    JMenu edit=new JMenu("编辑");
    JMenu play = new JMenu("播放");

    JMenuItem creat=new JMenuItem("新建");
    JMenuItem open=new JMenuItem("打开");
    JMenuItem save=new JMenuItem("保存");
    JMenuItem sta=new JMenuItem("开始");
    JMenuItem write=new JMenuItem("注释");





    //定义按钮
    JButton addSibingNodeBtn=new JButton("添加兄弟节点");
    JButton addChildBtn=new JButton("添加子节点");
    JButton deleteBtn=new JButton("删除节点");
    JButton editBtn=new JButton("编辑当前节点");

    
    
    MyVideoFrame videoframe = new MyVideoFrame("播放",china);
    
    public void init(){
        //设置窗口属性
    	videoframe.setVisible(false);
        System.out.println(ScreenUtils.getScreenWidth()+"\n"+ScreenUtils.getScreenHeight());
        jf.setBounds(0,0,WIDTH,HEIGHT);
        sp.setDividerLocation(WIDTH/2);
        
        
        //组装菜单栏
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.showOpenDialog(jf);
                File selectedFile = jFileChooser.getSelectedFile();

            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.showSaveDialog(jf);
                File selectedFile = jFileChooser.getSelectedFile();
            }
        });
        
        sta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//播放首个视频
				videoframe.setVisible(true);
				videoframe.now_play_TreeNode = china;
		        videoframe.play();
			}
		});
        file.add(creat);
        file.add(open);
        file.add(save);

        edit.add(write);

        play.add(sta);
        
        main_menu.add(file);
        main_menu.add(edit);
        main_menu.add(play);

        jf.setJMenuBar(main_menu);


        //创建JTree对象
        JTree jTree = new JTree(china);
        jTree.setEditable(true);
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
        	
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //得到当前选中节点对象
                //获取当前选中路径
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();

                //从根节点开始枚举节点
                Enumeration<TreeNode> treeNodeEnumeration = china.depthFirstEnumeration();
                
                //寻找与选中节点匹配的节点
                while(treeNodeEnumeration.hasMoreElements()){
                	MyTreeNode node;
                    node=(MyTreeNode) treeNodeEnumeration.nextElement();
                    
                    String cur_video = node.mystring;
                    JLabel vi = new JLabel("当前视频为"+cur_video);
                    JLabel[] action_arr = null;
                    JLabel has_action = new JLabel("是否已添加行为"+node.has_action);
                	
                    if(node.equals(lastPathComponent)){
                        //每个节点的素材及行为添加界面
                        JPanel jp=new JPanel();

                        Box verticalBox = Box.createVerticalBox();
                        //设置两个按钮
                        JButton new_file_btn=new JButton("添加素材");
                        JButton new_act_btn=new JButton("添加行为");

                        new_file_btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //打开素材选择界面
                                JFileChooser jFileChooser=new JFileChooser();
                                jFileChooser.showOpenDialog(jf);
                                File selectedFile = jFileChooser.getSelectedFile();
                                //保存选中素材
                                node.mystring = selectedFile.toString();
                                vi.setText("当前视频为"+node.mystring);
                                jf.revalidate();
                            }
                        });
                        
                        new_act_btn.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								node.TextSet = new textSetting();
								node.TextSet.VideoFrame =videoframe;
								node.TextSet.show_it(false,node.getChildCount());
								for(int i=0;i<node.getChildCount();i++)
		                        	action_arr[i] = new JLabel("第"+(i+1)+"种选择为"+node.TextSet.text[i]);
								node.has_action = true;
								has_action.setText("是否已添加行为"+node.has_action);
								jf.revalidate();
							}
						});
                        //提示节点间关系
                        String cur_str=node.toString();
                        JLabel cur;
                        JLabel fa;
                    
                        
                        if(node.getParent()==null){
                            cur=new JLabel("当前层次为："+cur_str);
                            fa=new JLabel("");
                        }
                        else{
                            String fa_str=node.getParent().toString();
                            cur=new JLabel("当前层次为："+cur_str);
                            fa=new JLabel("上一层次为："+fa_str);
                        }
                     
                        
                        //组装素材选择界面
                        Box horizontalBox = Box.createHorizontalBox();
                        horizontalBox.add(new_file_btn);
                        horizontalBox.add(new_act_btn);

                        Box verticalBox1 = Box.createVerticalBox();
                        verticalBox1.add(fa);
                        verticalBox1.add(cur);
                        verticalBox1.add(vi);
                        verticalBox1.add(has_action);
                        /*if(node.has_action)
                        	for(int i=0;i<node.getChildCount();i++)
                        		verticalBox1.add(action_arr[i]);*/


                        verticalBox.add(verticalBox1);
                        verticalBox.add(horizontalBox);
                        jp.add(verticalBox);
                        jp.revalidate();
                        sp.setRightComponent(new JScrollPane(jp));
                        sp.setDividerLocation(WIDTH/2);
                    }
                }
            }
        });


        DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();

        //设置底部节点的增删改
        //处理兄弟添加
        addSibingNodeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加兄弟节点逻辑

                //1、获取当前选中的节点
            	MyTreeNode selectedNode = (MyTreeNode) jTree.getLastSelectedPathComponent();
                if(selectedNode==null){
                    return;
                }
                //2、获取当前节点的父节点
                MyTreeNode parentNode = (MyTreeNode) selectedNode.getParent();
                if(parentNode==null){
                    return ;
                }

                //3、创建新节点
                MyTreeNode newNode=new MyTreeNode("新节点");

                //4、把新节点通过父节点进行添加
                int index = parentNode.getIndex(selectedNode);
                model.insertNodeInto(newNode,parentNode,index);

                //5、显示新节点
                TreeNode[] pathToRoot = model.getPathToRoot(newNode);
                TreePath treePath = new TreePath(pathToRoot);
                jTree.scrollPathToVisible(treePath);

                //6、重绘tree
                jTree.updateUI();
            }
        });

        addChildBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //为选中节点添加子节点
                //1、获取选中节点
            	MyTreeNode selectedNode = (MyTreeNode) jTree.getLastSelectedPathComponent();
                if(selectedNode==null){
                    return;
                }

                //2、创建新节点
                MyTreeNode newNode=new MyTreeNode("新节点");

                //3、把新节点添加到当前节点中
                selectedNode.add(newNode);

                //4、显示新节点
                TreeNode[] pathToRoot = model.getPathToRoot(newNode);
                TreePath treePath = new TreePath(pathToRoot);
                jTree.scrollPathToVisible(treePath);

                //5、重绘UI
                jTree.updateUI();
            }
        });


        //处理删除
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MyTreeNode selectedNode = (MyTreeNode) jTree.getLastSelectedPathComponent();

                if(selectedNode!=null&&selectedNode.getParent()!=null){
                    model.removeNodeFromParent(selectedNode);
                }

            }
        });

        //处理编辑
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前节点的路径
                TreePath selectionPath = jTree.getSelectionPath();

                //判断若路径不为空，则设置该路径的最后一个节点可编辑
                if(selectionPath!=null){
                    jTree.startEditingAtPath(selectionPath);
                }
            }
        });

        //组装初始界面
        sp.setContinuousLayout(true);
        sp.setLeftComponent(new JScrollPane(jTree));
        sp.setRightComponent(new JScrollPane(new JLabel("欢迎")));
        jf.add(sp);

        JPanel horizontalBox = new JPanel();
        horizontalBox.add(addSibingNodeBtn);
        horizontalBox.add(addChildBtn);
        horizontalBox.add(deleteBtn);
        horizontalBox.add(editBtn);

        jf.add(horizontalBox, BorderLayout.SOUTH);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}