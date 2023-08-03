package com.crio.shorturl;

import java.util.HashMap;
import java.util.Random;

public class XUrlImpl implements XUrl{
    protected HashMap<String,String> di=new HashMap<String,String>();
    protected HashMap<String,Integer> count=new HashMap<String,Integer>();
    @Override
    public String registerNewUrl(String longUrl) {
        if(di.containsKey(longUrl)){
            return di.get(longUrl);
        }
        Random rand = new Random();
        int num = rand.nextInt(1000000);
        String Er=""+num;
  
        di.put(longUrl,Er);
        int n=di.get(longUrl).hashCode();
        String s=Integer.toHexString(n);
        if(s.length()>9){
            s=s.substring(0,10);
        }
        int m=9-s.length();
        for(int i=0;i<m;i++){
            s+="b";
        }
        s="http://short.url/"+s;
        di.put(longUrl,s);
        count.put(longUrl,0);
        return s;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(di.containsValue(shortUrl)){
        return null;
        }
        di.put(longUrl,shortUrl);
        count.put(longUrl,0);
        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        for (String l : di.keySet())
        {
            
            if (shortUrl.equals(di.get(l))) {
                count.put(l,count.get(l)+1);
                return l;
            }
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(count.containsKey(longUrl)){
            return count.get(longUrl);
        }
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        di.remove(longUrl);
        return null;
    }
    
}
