## 集合的线程安全

解决方案
* Vector 
  * List<String> list = new Vector<>();
* Collections
  * List<String> list = Collections.synchronizedList(new ArrayList<>());
* CopyOnWriteArrayList 
  * List<String> list = new CopyOnWriteArrayList();


## HashSet的线程安全
解决方案
* CopyOnWriteArraySet
  * Set hashSet = new CopyOnWriteArraySet();


## HashMap的线程安全
解决方案
* ConcurrentHashMap
  * Map<String, Object> hashMap = new ConcurrentHashMap<>();