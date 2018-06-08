package JavaOrganizer.repository;

import JavaOrganizer.exception.RepositoryException;

public interface CalendarRepository {

	public void importObjects() throws RepositoryException;
	
	public void exportObjects() throws RepositoryException;
	
}
