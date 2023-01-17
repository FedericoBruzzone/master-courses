public interface ResourcePool {
   public Resource getResource(String type);
   public void releaseResource(String type, Resource r);
}
