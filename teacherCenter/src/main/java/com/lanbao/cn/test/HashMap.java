package com.lanbao.cn.test;

public class HashMap {
	
	transient Entry[] entrys;
	
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	static final int MAX = 1<<20;
	  
	int i=0;
	
	public HashMap(){
		entrys = new Entry[MAX];
	}
	
	public void put(Object k,Object v){
		int hash = hash(k.hashCode());
		entrys[hash] = new Entry(k,v);
	}
	
   static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

	public Object get(Object k){
		int hash = hash(k.hashCode());
		return entrys[hash].v;
	}
	
	class Entry<K,V>{
		Object k = null;
		Object v = null;
		Entry(Object v2,Object k2){
			this.k = v2;
			this.v = k2;
		}
	}
	
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("name", "hello");
		System.out.println(map.get("name"));
	}
}
