import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AutMouse {
	 static String st1 ="";
	  static int a = 0;
  public static void main(String[] args) throws AWTException, IOException, InterruptedException {

	  Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
			Robot robot = new Robot();


			@Override
			public void run() {

				  try {
					  java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
					  java.awt.DisplayMode displayMode = env.getDefaultScreenDevice().getDisplayMode();
					  // 変数widthとheightに画面の解像度の幅と高さを代入
					  int width = displayMode.getWidth();
					  int height = displayMode.getHeight();
					  System.out.println(width);
					  System.out.println(height);
					  move((int)(width*0.83), (int)(height*0.7) ,1);
			          //ファイルを読み込む
			          FileReader fr = new FileReader("C:\\Users\\mnuus\\AppData\\Roaming\\MetaQuotes\\Terminal\\34B08C83A5AAE27A4079DE708E60511E\\MQL4\\Files\\HighLow.txt");
			          BufferedReader br = new BufferedReader(fr);

			          //読み込んだファイルを１行ずつ画面出力する
			          String line;

			          line = br.readLine() ;
			          int hantei = 0;

			          if(line.length() == st1.length())
			          {
			        	  for(int i = 0;i < line.length();i++)
			              {
			        		  if(line.charAt(i) != st1.charAt(i))
			        		  {
			        			  hantei = 1;
			        		  }
			              }
			          }
			          else
			          {
			        	  hantei = 1;
			          }

			          if(line == "reload")hantei = 0;//mt4のほうで別のチャートにしたときマウスが動かないように

			          st1 = line;
			          if(hantei == 1 && a != 0)
			          {

			        	  	if(line.charAt(0) =='B')
			        	  	{
			        	  		move((int)(width*0.73), (int)(height*0.7) ,1);
				        	    // 次の動作の前に0.5秒待つ
				        	    robot.delay(100);
				        	    // マウスの左クリック
				        	    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				        	    // 左クリック状態の解除
				        	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			        	  	}
			        	  	if(line.charAt(0) =='S')
			        	  	{
			        	  		move((int)(width*0.83), (int)(height*0.7) ,1);
				        	    // 次の動作の前に0.5秒待つ
				        	    robot.delay(100);
				        	    // マウスの左クリック
				        	    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				        	    // 左クリック状態の解除
				        	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			        	  	}
			          }

			          //終了処理
			          br.close();
			          fr.close();

			      } catch (IOException ex) {
			          //例外発生時処理
			          ex.printStackTrace();
			      }
				  if(a == 0)a =1;
			}
		};
		timer.schedule(task, 0, 10000);






    // マウスを画面の中央に移動させる

    // マウスを画面の中央に移動させる

    /*move(1400, 750 ,1);


    // 次の動作の前に0.5秒待つ
    robot.delay(100);

    // マウスの左クリック
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    // 左クリック状態の解除
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    move(1600, 750 ,1);
    // 次の動作の前に0.5秒待つ
    robot.delay(100);
    //System.out.println("Location of Mouse : " + pointerInfo.getLocation());

    // マウスの左クリック
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    // 左クリック状態の解除
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    */
  }




  static void move(int moW,int moH,int moD){
      Robot r;
      try {
          r = new Robot();
      }
      catch (AWTException e) {
          e.printStackTrace();
          return;
      }
      PointerInfo pI = MouseInfo.getPointerInfo();
      Point point = pI.getLocation();

          int i = (int)point.getX();
          int h = (int)point.getY();
      // move
      while (!(i == moW && h == moH)) {
          if (i < moW){
              i++;
          }else if (i > moW) {
              i--;
          }
          if (h < moH){
              h++;
          }else if (h > moH) {
              h--;
          }
          r.mouseMove(i,h);
          r.delay(moD);
      }
  }
}