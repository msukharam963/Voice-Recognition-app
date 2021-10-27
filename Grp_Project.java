package grp_project;
import java.io.IOException;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

class App extends Thread{
    public void run(){
        Voice();
    }
protected void Voice()
{
 Configuration config = new Configuration();
		
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("src\\resources\\1295.dic");
		config.setLanguageModelPath("src\\resources\\1295.lm");
        
		try {
			LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
			speech.startRecognition(true);
			
			SpeechResult speechResult = null;
                        outer:
			while((speechResult=speech.getResult()) != null) {
				String VoiceCommand = speechResult.getHypothesis();
			 	System.out.println("Voice Command is: " + VoiceCommand);
				
				if(VoiceCommand.equalsIgnoreCase("Open Edge")) {
					Runtime.getRuntime().exec("cmd.exe /c start msedge www.google.com");
				}else if(VoiceCommand.equalsIgnoreCase("Close Edge")) {
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM msedge.exe");
				}
                                else if(VoiceCommand.equalsIgnoreCase("Open calculator")) {
					Runtime.getRuntime().exec("cmd.exe /c calc");
				}else if(VoiceCommand.equalsIgnoreCase("Close calculator")) {
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /F /IM calculator.exe");
				}
                                else if(VoiceCommand.equalsIgnoreCase("Open gmail")) {
					Runtime.getRuntime().exec("cmd.exe /c start msedge www.gmail.com");
				}else if(VoiceCommand.equalsIgnoreCase("Close gmail")) {
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM msedge.exe");
				}
                                else if(VoiceCommand.equalsIgnoreCase("Open store")) {
					Runtime.getRuntime().exec("cmd.exe /c start ms-windows-store:");
				}else if(VoiceCommand.equalsIgnoreCase("Close store")) {
					Runtime.getRuntime().exec("cmd.exe /c taskkill /f /im WinStore.App.exe");
				}
                                else if(VoiceCommand.equalsIgnoreCase("stop sensing")){
                                    break outer;   
                                     }
                        }
				
		}catch (IOException e) {
			e.printStackTrace();
		}   
}
public void intro(){
    System.out.println("This Code supports few command at this stage");
    System.out.println("1.Open/close edge\n"+"2.open/close gmail\n"+"3.open/close gmail\n"+"4.open/close calculator\n"+"5.open/close store");
} 
}
class feedback implements Runnable{
    public void run()
    {
        System.out.println("Thank you for using the Code");
    }
}
public class Grp_Project extends App{
     public static void main(String[] args) throws InterruptedException {
      Grp_Project g=new Grp_Project();
      g.intro();
      Thread.sleep(2000);
      g.start();
      g.join();
      feedback f=new feedback();
      Thread t=new Thread(f);
      t.start();
      t.join();
    }
}
