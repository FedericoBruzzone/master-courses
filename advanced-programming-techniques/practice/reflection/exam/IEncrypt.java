interface IEncrypt {
    public long getKey(); 
    public void setKey(long l);
    public byte[] encrypt(byte[] b);
 }
