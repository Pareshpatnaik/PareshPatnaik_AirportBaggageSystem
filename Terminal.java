package com.baggagesystem.entities;

public final class Terminal 
{
	private final String name;
	
	public Terminal(String name) 
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
			return false;
		if(this == object)
			return true;
		if(getClass() != (object.getClass()))
			return false;
		
		Terminal terminal = (Terminal)object;
		if(this.name.equalsIgnoreCase(terminal.getName()))
			return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Terminal [ "+name+" ]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 7;
		result = prime * result + name.hashCode();
		return result;
	}
}
