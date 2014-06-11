package br.unicamp.mc437.sessionfake;

import java.util.HashMap;
import java.util.Map;

public class SessionFake {
	
	private final Map<String,Object> session;
	
	public SessionFake(){
		session  = new HashMap<String,Object>();
	}
	
	public Map<String,Object> getSession(){
		return session;
	}
	
	public void setAttribute(String str, Object obj){
		session.put(str, obj);
	}
	
	public Object getAttribute(String str){
		return session.get(str);
	}
	
	public void removeAttribute(String str){
		session.remove(str);
	}

}
