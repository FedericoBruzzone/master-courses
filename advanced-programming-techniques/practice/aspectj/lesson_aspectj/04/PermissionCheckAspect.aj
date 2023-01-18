public privileged aspect PermissionCheckAspect {
  /*private pointcut PermissionCheckedExecution():*/
		/*(execution(public int Account.getAccountNumber()) ||*/
     /*execution(public void Account.credit(float))     ||*/
		 /*execution(public void Account.debit(float) throws InsufficientBalanceException) ||*/
		 /*execution(public float Account.getBalance()) &&*/
		 /*within(Account));*/

	private pointcut PermissionCheckedExecution():
		(execution(public * Account.*(..)) &&
		 !execution(String Account.toString()) &&
		 within(Account));


	before(): PermissionCheckedExecution() {
    AccessController.checkPermission(new BankingPermission("accountOperation"));
	}

	declare warning:
		call(void AccessController.checkPermission(java.security.Permission)) &&
		within(Account) &&
		!within(PermissionCheckAspect):
			"Do not AccessController.checkPermission(..) from Account";	
}
