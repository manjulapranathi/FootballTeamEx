package com.football;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Produces("application/json")
@Path("/FootBallService")
public class FootBallService {
	FootBallDao footballDao = new FootBallDao();
	private static final String SUCCESS_RESULT="<result>Successfully created FootBall team</result>";
	private static final String FAILURE_RESULT="<result>Failed to create FootBall Team</result>";
	private static final Logger logger = Logger.getLogger(FootBallService.class.getName());
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("/footballteams")
	@Produces("application/json")
	public Response getAllFootBallTeams(){
		List<FootBallTeam>  footBallTeamList;
	    GenericEntity<List<FootBallTeam>> footBallTeamEntity;

	    footBallTeamList = footballDao.getAllFootBallTeams();
	    if(footBallTeamList != null) {
		    footBallTeamEntity  = new GenericEntity<List<FootBallTeam>>(footBallTeamList) { };
	
		    return Response.ok( footBallTeamEntity ).build();
	    }
	    return null; 
	    
		//return footballDao.getAllFootBallTeams();
	}
	
	 @GET
	 @Path("/footballteams/{teamname}")
	 @Produces("application/json")
	 public FootBallTeam getUser(@PathParam("teamname") String name){
		logger.info("In getUser(...) of FootBallService: PathParm - team name - "+name); 
	    return footballDao.getFootBallTeam(name);
	 }

	 @POST
	 @Path("/footballteams")
     @Produces("application/json")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 public String createUser(@FormParam("name") String name,
	    @FormParam("city") String city,
	    @FormParam("owner") String owner,
	    @FormParam("competition") String competition,
	    @FormParam("no_of_players") Integer no_of_players,	    
	    @Context HttpServletResponse servletResponse) throws IOException{
		 
		logger.info("Entering createUser(...) of FootBallService");
 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = dateFormat.format(new Date()); 
		FootBallTeam footBallTeam = new FootBallTeam(name, city, owner, competition, 
				no_of_players, dateStr);
	    int result = footballDao.addFootBallTeam(footBallTeam);
	    
	    if(result == 1){
	    	logger.info("Successfully created a Football team");	
	    	URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
			Response.created(uri).build();
			servletResponse.sendRedirect(uri.toString());
            return SUCCESS_RESULT;
	    }
	    logger.info("Failed to create a Football team");
	    return FAILURE_RESULT;
	 }

}
