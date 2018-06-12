package JavaOrganizer.model;

import java.time.LocalDateTime;

import JavaOrganizer.controller.CalendarManager;

public class Event implements Comparable<Event> {
	private long m_Id;
	private String m_title;
	private String m_description;
	private String m_location;
	private LocalDateTime m_startingDate;
	private LocalDateTime m_endingDate;
	private LocalDateTime m_remindDate;
	
	


	public Event(String title, String descr, String loc,
			LocalDateTime startDate, LocalDateTime endDate, LocalDateTime remindTime) {
		this.setId(CalendarManager.nextEventId);
		++CalendarManager.nextEventId;
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setEndingDate(endDate);
		this.setRemindDate(remindTime);
	}

	
	
	public Event(long id,String title, String descr, String loc,
			LocalDateTime startDate, LocalDateTime endDate, LocalDateTime remindTime) {
		this.setId(id);
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setEndingDate(endDate);
		this.setRemindDate(remindTime);
	}
	
	public Event(long id,String title, String descr, String loc,
			LocalDateTime startDate, LocalDateTime remindTime) {
		this.setId(id);
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setRemindDate(remindTime);
	}
	
	
	
	public String convertToOutlookFormat()
	{
		return "";
	}
	
	
	
	public Event clone() {
		return new Event(getId(), getTitle(), getDescription(),
				getLocation(), getStartingDate(), getEndingDate(), getRemindDate());
	}
	
	public String toString() {
		String result = String.format("%-5d | TYTUŁ: %s, OPIS: %s, MIEJSCE: %s", m_Id, m_title, m_description, m_location);
//		String result = "Tytul: " + m_title + ", opis: " + m_description;
		return result;
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

	public String getLocation() {
		return m_location;
	}

	public void setLocation(String m_location) {
		this.m_location = m_location;
	}
	
	public long getId() {
		return m_Id;
	}

	public void setId(long m_Id) {
		this.m_Id = m_Id;
	}

	public LocalDateTime getRemindDate() {
		return m_remindDate;
	}


	public void setRemindDate(LocalDateTime m_remindDate) {
		this.m_remindDate = m_remindDate;
	}

		
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return 0;
	}




	
}
