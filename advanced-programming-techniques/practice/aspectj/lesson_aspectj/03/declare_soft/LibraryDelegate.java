public class LibraryDelegate {

	private LibrarySession _session;

	public LibraryDelegate() throws LibraryException { init(); }

	private void init() throws LibraryException {
    LibrarySessionHome home
      = (LibrarySessionHome)ServiceLocator.getInstance().
        getRemoteHome("Library", LibrarySessionHome.class);
    _session = home.create();
  }

	public LibraryTO getLibraryDetails() throws LibraryException {
    return _session.getLibraryDetails();
  }

	public void setLibraryDetails(LibraryTO to) throws LibraryException {
    _session.setLibraryDetails(to);
  }

	public void addBook(BookTO book) throws LibraryException {
    _session.addBook(book);
  }

	public void removeBook(BookTO book) throws LibraryException {
    _session. removeBook(book);
  }

}

