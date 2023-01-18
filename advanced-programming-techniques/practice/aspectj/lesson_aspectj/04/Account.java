public class Account {
  private int _accountNumber;
  private float _balance;

  public Account(int accountNumber) {
    _accountNumber = accountNumber;
  }

  public int getAccountNumber() {
    return _accountNumber;
  }

  public void credit(float amount) {
    _balance = _balance + amount;
  }

  public void debit(float amount) throws InsufficientBalanceException {
    if (_balance < amount) throw new InsufficientBalanceException();
    else _balance = _balance - amount;
  }

  public float getBalance() {
    return _balance;
  }

  public String toString() {
    return "Account: " + _accountNumber;
  }
}
