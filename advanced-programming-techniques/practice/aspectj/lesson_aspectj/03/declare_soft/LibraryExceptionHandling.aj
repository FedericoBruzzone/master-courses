privileged public aspect LibraryExceptionHandling {

	declare soft : RemoteException :
    call(* *.*(..) throws RemoteException) && within(LibraryDelegate);

	declare soft : ServiceLocatorException :
    call(* *.*(..) throws ServiceLocatorException) && within(LibraryDelegate);

	declare soft : CreateException :
    call(* *.*(..) throws CreateException) && within(LibraryDelegate);

  after() throwing(SoftException ex) throws LibraryException :
    execution(* LibraryDelegate.*(..) throws LibraryException) && within(LibraryDelegate) {
      throw new LibraryException(ex.getWrappedThrowable());
    }

}
