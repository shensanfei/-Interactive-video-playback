package n;

import javax.swing.tree.DefaultMutableTreeNode;

public class MyTreeNode extends DefaultMutableTreeNode {
	//视频地址
	public String mystring;
	//对应选项类
	public textSetting TextSet;
	//是否添加行为
	public boolean has_action;
		
	MyTreeNode(String title) {
		super(title);
		has_action = false;
	}
	
	
}
