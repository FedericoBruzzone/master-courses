interface IDecrypt {
    public long getKey();

    public void setKey(long key);

    public byte[] decrypt(byte[] bytecode);
}
