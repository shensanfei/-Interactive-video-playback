package n;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class MyVideoFrame extends JFrame {
	int WIDTH=ScreenUtils.getScreenWidth();
    int HEIGHT=ScreenUtils.getScreenHeight()-50;
    
    //播放组件控制
    private EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    
    //当前进行的节点
    public MyTreeNode now_play_TreeNode;
    
	public MyVideoFrame(String title,MyTreeNode node) {
		super(title);
		this.now_play_TreeNode = node;
		this.setBounds(0,0,WIDTH,HEIGHT);
        this.setContentPane(mediaPlayerComponent);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
       

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

//        JPanel controlsPane = new JPanel();
//        JButton pauseButton = new JButton("Pause");
//        controlsPane.add(pauseButton);
//        JButton rewindButton = new JButton("Rewind");
//        controlsPane.add(rewindButton);
//        JButton skipButton = new JButton("Skip");
//        controlsPane.add(skipButton);
//        contentPane.add(controlsPane, BorderLayout.SOUTH);
//        
//        //添加暂停，快进与快退
//        pauseButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mediaPlayerComponent.getMediaPlayer().pause();
//            }
//        });
//
//        rewindButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mediaPlayerComponent.getMediaPlayer().skip(-10000);
//            }
//        });
//
//        skipButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mediaPlayerComponent.getMediaPlayer().skip(10000);
//            }
//        });
        
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
        	//实现播放完成后的控制切换
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	now_play_TreeNode.TextSet.show_it(true,now_play_TreeNode.getChildCount());
                    }
                });
            }
         });
        this.setContentPane(contentPane);
        this.setVisible(true);
	}
	
	public void play() {
		
		mediaPlayerComponent.getMediaPlayer().playMedia(now_play_TreeNode.mystring);
		
	}
	
}
