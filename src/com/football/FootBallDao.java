package com.football;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class FootBallDao {
	
	private static final Logger logger =
	        Logger.getLogger(FootBallDao.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<FootBallTeam> getAllFootBallTeams() {
		logger.info("Entering getAllFootBallTeams method");
		List<FootBallTeam> footballTeamList = null;
		try {
			File file = new File("FootBallTeams.txt");
			if(!file.exists()){
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dateStr = dateFormat.format(new Date());
				FootBallTeam team = new FootBallTeam("Liverpoolpublic","Liverpool", "John",
						"Manchester", 14, dateStr);
				footballTeamList = new ArrayList<FootBallTeam>();
				footballTeamList.add(team);
				saveTeams(footballTeamList);
			} 
			else {
				FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            footballTeamList = (ArrayList<FootBallTeam>) ois.readObject();
	            ois.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();		
		}
		return footballTeamList;
	}
	
	public FootBallTeam getFootBallTeam(String name){
	      List<FootBallTeam> footballTeams = getAllFootBallTeams();

	      for(FootBallTeam footballTeam: footballTeams){
	         if(footballTeam.getName().equals(name)){
	            return footballTeam;
	         }
	      }
	      return null;
	}
	
	public int addFootBallTeam(FootBallTeam footballTeam){
	      List<FootBallTeam> footballTeams = getAllFootBallTeams();
	      boolean teamExists = false;
	      for(FootBallTeam team: footballTeams){
	         if(team.getName().equals(footballTeam.getName())){
	        	teamExists = true;
	            break;
	         }
	      }		
	      if(!teamExists){
	    	  footballTeams.add(footballTeam);
	    	  saveTeams(footballTeams);
	          return 1;
	      }
	      return 0;
	}
	
	public void saveTeams(List<FootBallTeam> teamList){
		
		try {
	         File file = new File("FootBallTeams.txt");
	         FileOutputStream fos = new FileOutputStream(file);

	         ObjectOutputStream outputStream = new ObjectOutputStream(fos);
	         outputStream.writeObject(teamList);
	         outputStream.close();
	         logger.info("Created Teams");
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
	}
	

}
