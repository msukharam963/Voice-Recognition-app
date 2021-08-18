package com.example.voice.VoiceAssistant;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class App 
{
    public static void main( String[] args )
    {
Configuration config = new Configuration();
		
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("src\\main\\resources\\1295.dic");
		config.setLanguageModelPath("src\\main\\resources\\1295.lm");
        
		try {
			LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
			speech.startRecognition(true);
			
			SpeechResult speechResult = null;
			
			while((speechResult=speech.getResult()) != null) {
				String VoiceCommand = speechResult.getHypothesis();
			 	System.out.println("Voice Command is: " + VoiceCommand);
				
				if(VoiceCommand.equalsIgnoreCase("Open Edge")) {
					Runtime.getRuntime().exec("cmd.exe /c start msedge www.google.com");
				}else if(VoiceCommand.equalsIgnoreCase("Close Edge")) {
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM msedge.exe");
				}
			}
				
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
}
