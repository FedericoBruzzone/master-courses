interface IEncrypt {
    public long getKey();

    public void setKey(long key);

    public byte[] encrypt(byte[] bytecode);
}
