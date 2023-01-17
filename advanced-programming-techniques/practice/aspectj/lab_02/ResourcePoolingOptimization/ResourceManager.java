import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.crypto.spec.ChaCha20ParameterSpec;

import java.util.ArrayList;
import java.util.Arrays;

public class ResourceManager implements ResourcePool {
  
  HashMap<String, ArrayList<Resource>> cache = new HashMap<String, ArrayList<Resource>>();

  private boolean empty(String type) {
    return !cache.containsKey(type); 
  } 

  public Resource getResource(String type) {
    if (!empty(type)) {
      ArrayList<Resource> tmp = cache.get(type);
       Resource r = tmp.remove(0);
      cache.put(type, tmp);
      return r;
    }
    return null;
  }

  public void releaseResource(String type, Resource r) {
    ArrayList al = cache.get(type);
    if (empty(type)) {
      al = new ArrayList<>();
    }
    al.add(r); 
    cache.put(type, al);
  }
} 
