package com.football;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "footballteam")
public class FootBallTeam  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String city;
	private String owner;
	private String competition;
	private Integer no_of_players;
	private String date_of_creation;
	
	FootBallTeam(){
		
	}
	
	FootBallTeam(String name, String city, String owner, String competition,
			Integer no_of_players, String date_of_creation){
		this.name = name;
		this.city = city;
		this.owner = owner;
		this.competition = competition;
		this.no_of_players = no_of_players;
		this.date_of_creation = date_of_creation;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@XmlElement
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	
	@XmlElement
	public Integer getNo_of_players() {
		return no_of_players;
	}
	public void setNo_of_players(Integer no_of_players) {
		this.no_of_players = no_of_players;
	}
	
	@XmlElement
	public String getDate_of_creation() {
		return date_of_creation;
	}
	public void setDate_of_creation(String date_of_creation) {
		this.date_of_creation = date_of_creation;
	}
}
