package organizer.model;

import java.time.LocalDateTime;

public class Event {
	private String m_title;
	private String m_description;
	private String m_location;
	private LocalDateTime m_startingDate;
	private LocalDateTime m_endingDate;
	
	public Event(String title, String descr, String loc,
			LocalDateTime startDate, LocalDateTime endDate) {
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setEndingDate(endDate);
	}

	public String convertToOutlookFormat()
	{
		return "";
	}
	
	// ************************* GETTERY I SETTERY  *************************
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

	public LocalDateTime getStartingDate() {
		return m_startingDate;
	}

	public void setStartingDate(LocalDateTime m_startingDate) {
		this.m_startingDate = m_startingDate;
	}

	public LocalDateTime getEndingDate() {
		return m_endingDate;
	}

	public void setEndingDate(LocalDateTime m_endingDate) {
		this.m_endingDate = m_endingDate;
	}

	private String getLocation() {
		return m_location;
	}

	private void setLocation(String m_location) {
		this.m_location = m_location;
	}
}
