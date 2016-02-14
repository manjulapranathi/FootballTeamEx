package com.football;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class TestFootBallWebservice {
	
	   private static final Logger logger =
	        Logger.getLogger(FootBallDao.class.getName());
	   private Client client;
	   private String REST_SERVICE_URL = "http://localhost:8080/FootballTeamEx/rest/FootBallService/footballteams";
	   private static final String SUCCESS_RESULT="<result>success</result>";
	   private static final String PASS = "pass";
	   private static final String FAIL = "fail";

	   private void init(){
	      this.client = ClientBuilder.newClient();
	   }

	   public static void main(String[] args){
		  TestFootBallWebservice tester = new TestFootBallWebservice();
		  logger.info("initialize the tester");
	      tester.init();
	      logger.info("test get all FootBallTeams Web Service Method");
	      tester.testGetAllFootBallTeams();
	      logger.info("test get FootBallTeam Web Service Method"); 
	      tester.testGetFootBallTeam();	     
	      logger.info("test add FootBallTeam Web Service Method");
	      tester.testAddFootBallTeam();
	      
	   }
	   
	   //Test: Get list of all FootBallTeams
	   //Test: Check if list is not empty
	   private void testGetAllFootBallTeams(){
	      GenericType<List<FootBallTeam>> list = new GenericType<List<FootBallTeam>>() {};
	      List<FootBallTeam> FootBallTeams = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_JSON)
	         .get(list);
	      String result = PASS;
	      if(FootBallTeams.isEmpty()){
	         result = FAIL;
	      }
	      System.out.println("Test case name: testGetAllFootBallTeams, Result: " + result );
	   }
	   
	   
	   //Test: Get FootBallTeam of id 1
	   //Test: Check if FootBallTeam is same as sample FootBallTeam
	   private void testGetFootBallTeam(){
	      FootBallTeam getfootBallTeam = new FootBallTeam();
	      getfootBallTeam.setName("Liverpoolpublic");;

	      FootBallTeam footBallTeam = client
	         .target(REST_SERVICE_URL)
	         .path("/{name}")
	         .resolveTemplate("name", "Liverpoolpublic")
	         .request(MediaType.APPLICATION_JSON)
	         .get(FootBallTeam.class);
	      String result = FAIL;
	      if(footBallTeam != null && getfootBallTeam.getName().equals(footBallTeam.getName())){
	         result = PASS;
	      }
	      System.out.println("Test case name: testGetFootBallTeam, Result: " + result );
	   }
	   
	   
	   //Test: Add FootBallTeam of id 2
	   //Test: Check if result is success XML.
	   private void testAddFootBallTeam(){
	      Form form = new Form();
	      form.param("name", "SuperKings");
	      form.param("city", "London");
	      form.param("owner", "Sally");
	      form.param("competition", "Liverpoolpublic");
	      form.param("no_of_players", "14");
	      
	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_JSON)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	   
	      String result = PASS;
	      if(!SUCCESS_RESULT.equals(callResult)){
	         result = FAIL;
	      }

	      System.out.println("Test case name: testAddFootBallTeam, Result: " + result );
	   }
   
}
