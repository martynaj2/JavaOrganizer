package organizer.model;

import java.util.Date;

public class Event {
	private String m_title;
	private String m_description;
	private String m_location;
	private Date m_startingDate;
	private Date m_endingDate;
	
	public Event(String title, String descr, String loc, Date startDate, Date endDate) {
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setEndingDate(endDate);
	}

	public String getTitle() {
		return m_title;
	}

	public void setTitle(String m_title) {
		this.m_title = m_title;
	}

	public String getDescription() {
		return m_description;
	}

	public void setDescription(String m_description) {
		this.m_description = m_description;
	}

	public Date getStartingDate() {
		return m_startingDate;
	}

	public void setStartingDate(Date m_startingDate) {
		this.m_startingDate = m_startingDate;
	}

	public Date getEndingDate() {
		return m_endingDate;
	}

	public void setEndingDate(Date m_endingDate) {
		this.m_endingDate = m_endingDate;
	}

	private String getLocation() {
		return m_location;
	}

	private void setLocation(String m_location) {
		this.m_location = m_location;
	}
}
