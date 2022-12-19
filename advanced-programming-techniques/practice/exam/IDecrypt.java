interface IDecrypt {
   public long getKey(); 
   public void setKey(long l);
   public byte[] decrypt(byte[] b);
}
