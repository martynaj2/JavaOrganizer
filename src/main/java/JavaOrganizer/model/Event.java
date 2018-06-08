package JavaOrganizer.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
	private String m_title;
	private String m_description;
	private String m_location;
	private LocalDateTime m_startingDate;
	private LocalDateTime m_endingDate;
	private LocalDateTime m_remindDate;
	private long m_Id;
	


	public Event(String title, String descr, String loc,
			LocalDateTime startDate, LocalDateTime endDate, LocalDateTime remindTime) {
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setEndingDate(endDate);
		this.setRemindDate(remindTime);
	}

	
	
	public Event(long id,String title, String descr, String loc,
			LocalDateTime startDate, LocalDateTime endDate, LocalDateTime remindTime) {
		this.setM_Id(id);
		this.setTitle(title);
		this.setDescription(descr);
		this.setLocation(loc);
		this.setStartingDate(startDate);
		this.setEndingDate(endDate);
		this.setRemindDate(remindTime);
	}
	
	
	public String convertToOutlookFormat()
	{
		return "";
	}
	
	
	
	public Event clone() {
		return new Event(getM_Id(), getTitle(), getDescription(),
				getLocation(), getStartingDate(), getEndingDate(), getRemindDate());
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
	
	public long getM_Id() {
		return m_Id;
	}

	public void setM_Id(long m_Id) {
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
