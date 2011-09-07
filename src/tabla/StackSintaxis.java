package tabla;

import java.util.Stack;

public class StackSintaxis 
{
	private Stack stack = new Stack();
	
	public StackSintaxis()
	{
		stack.ensureCapacity(100);
		
	}
	
	public Stack darStack()
	{
		return stack;
	}
	
	public Object pop()
	{
		return stack.pop();
	}
	
	public Object peek()
	{
		return stack.peek();
	}
	
	public void push(Object valor)
	{
		stack.push(valor);
	}
	
	public void pop(int cont)
	{
		while (cont != 0)
		{
			stack.pop();
			cont--;
		}
	}

	public void remover(int profundidad)
	{
		stack.remove(profundidad);
	}
	
	
}
