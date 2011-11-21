//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//               Ámbito global
//a = global[a]
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//*******************************************
//Método: main
//Tipo de retorno: void
//b = stack[]
//b = stack[1]
//t|_1 = stack[2]
//t|_2 = stack[3]
//*******************************************
main proc near
//### if(<exp>) <block1> else <block2> ####
	//=== <boolean exp> (or) ========
	//=== <boolean exp> (and) =======
	t|_1 = 5 + 5
	if 1 != t|_1 goto E_main_2
	t|_1 = 0
	goto E_main_3
E_main_2:
	t|_1 = 1
E_main_3:
	t|_1 = 1 and t|_1
	//===============================
	if 1 < 2 goto E_main_4
	t|_2 = 0
	goto E_main_5
E_main_4:
	t|_2 = 1
E_main_5:
	t|_1 = t|_1 or t|_2
	//===============================
	if t|_1 == 0 goto E_main_0
//### <block1> ############################
//--- <assign> ----------------------------
	global[a] = 0
//-----------------------------------------
//--- <assign> ----------------------------
	stack[1] = 'a'
//-----------------------------------------
	goto E_main_1
E_main_0:
//### <block2> - else #####################
//--- <assign> ----------------------------
	stack[] = 5
//-----------------------------------------
//--- <assign> ----------------------------
	global[a] = 1
//-----------------------------------------
E_main_1:
//#########################################
//--- <assign> ----------------------------
	//+++ <metodoCall> ++++++++++++++
	param 2, suma, 1
	param 5, suma, 2
	t|_1 = call suma, 2
	//+++++++++++++++++++++++++++++++
	//+++ <metodoCall> ++++++++++++++
	param 2, factorial, 1
	t|_2 = call factorial, 1
	//+++++++++++++++++++++++++++++++
	t|_1 = t|_1 + t|_2
	global[a] = t|_1
//-----------------------------------------
E_main_$:
	ret
main endp
//*******************************************


//*******************************************
//Método: expresiones
//Tipo de retorno: void
//b = stack[]
//t|_1 = stack[1]
//t|_2 = stack[2]
//*******************************************
expresiones proc near
//--- <assign> ----------------------------
	stack[] = 1
//-----------------------------------------
//--- <assign> ----------------------------
	t|_1 = 1 * 2
	t|_2 = 3 / 2
	t|_2 = 4 + t|_2
	t|_2 = 3 * t|_2
	t|_1 = t|_1 + t|_2
	global[a] = t|_1
//-----------------------------------------
//--- <assign> ----------------------------
	t|_1 = global[a]
	t|_2 = 2 * 3
	t|_1 = t|_1 + t|_2
	global[a] = t|_1
//-----------------------------------------
//--- <assign> ----------------------------
	t|_1 = 0 - 1
	global[a] = t|_1
//-----------------------------------------
//--- <assign> ----------------------------
	//=== <boolean exp> (or) ========
	//+++ <metodoCall> ++++++++++++++
	param 1, suma, 1
	param 1, suma, 2
	t|_1 = call suma, 2
	//+++++++++++++++++++++++++++++++
	if 1 < t|_1 goto E_expresiones_0
	t|_1 = 0
	goto E_expresiones_1
E_expresiones_0:
	t|_1 = 1
E_expresiones_1:
	t|_2 = stack[]
	t|_1 = t|_1 or t|_2
	//===============================
	stack[] = t|_1
//-----------------------------------------
//### if(<exp>) <block1> else <block2> ####
	t|_1 = stack[]
	if t|_1 == 0 goto E_expresiones_2
//### <block1> ############################
//### if(<exp>) <block1> else <block2> ####
	t|_2 = stack[]
	if t|_2 != 1 goto E_expresiones_6
	t|_2 = 0
	goto E_expresiones_7
E_expresiones_6:
	t|_2 = 1
E_expresiones_7:
	if t|_2 == 0 goto E_expresiones_4
//### <block1> ############################
//--- <assign> ----------------------------
	global[a] = 5
//-----------------------------------------
	goto E_expresiones_5
E_expresiones_4:
//### <block2> - else #####################
//--- <assign> ----------------------------
	global[a] = 4
//-----------------------------------------
E_expresiones_5:
//#########################################
	goto E_expresiones_3
E_expresiones_2:
//### <block2> - else #####################
//--- <assign> ----------------------------
	global[a] = 3
//-----------------------------------------
E_expresiones_3:
//#########################################
E_expresiones_$:
	ret
expresiones endp
//*******************************************


//*******************************************
//Método: suma
//Tipo de retorno: int
//param: num1 = stack[]
//param: num2 = stack[1]
//resultado = stack[11]
//t|_1 = stack[3]
//t|_2 = stack[4]
//*******************************************
suma proc near
//--- <assign> ----------------------------
	t|_1 = stack[]
	t|_2 = stack[1]
	t|_1 = t|_1 + t|_2
	stack[11] = t|_1
//-----------------------------------------
	t|_1 = stack[11]
	return t|_1
	goto E_suma_$
E_suma_$:
	ret
suma endp
//*******************************************


//*******************************************
//Método: arreglos
//Tipo de retorno: void
//b = stack[]
//c = stack[5]
//t|_1 = stack[6]
//t|_2 = stack[7]
//*******************************************
arreglos proc near
//--- <assign> ----------------------------
	//°°° <location> - array °°°°°°°°
	t|_1 = 1 * 0
	t|_1 = t|_1 + 
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[t|_1] = '0'
//-----------------------------------------
//--- <assign> ----------------------------
	//°°° <location> - array °°°°°°°°
	t|_1 = 1 + 2
	t|_2 = 1 * t|_1
	t|_2 = t|_2 + 
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[t|_2] = '1'
//-----------------------------------------
//--- <assign> ----------------------------
	//°°° <location> - array °°°°°°°°
	//+++ <metodoCall> ++++++++++++++
	param 1, suma, 1
	param 2, suma, 2
	t|_1 = call suma, 2
	//+++++++++++++++++++++++++++++++
	t|_2 = 1 * t|_1
	t|_2 = t|_2 + 
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[t|_2] = '2'
//-----------------------------------------
//--- <assign> ----------------------------
	global[a] = 3
//-----------------------------------------
//--- <assign> ----------------------------
	//=== <boolean exp> (and) =======
	//+++ <metodoCall> ++++++++++++++
	t|_2 = global[a]
	param t|_2, factorial, 1
	t|_1 = call factorial, 1
	//+++++++++++++++++++++++++++++++
	if t|_1 <= 5 goto E_arreglos_0
	t|_1 = 0
	goto E_arreglos_1
E_arreglos_0:
	t|_1 = 1
E_arreglos_1:
	t|_1 = not t|_1
	t|_1 = 1 and t|_1
	t|_1 = 1 and t|_1
	//===============================
	stack[5] = t|_1
//-----------------------------------------
E_arreglos_$:
	ret
arreglos endp
//*******************************************


//*******************************************
//Método: estructura
//Tipo de retorno: void
//a = stack[]
//b = stack[1]
//t|_1 = stack[15]
//t|_2 = stack[16]
//t|_3 = stack[17]
//*******************************************
estructura proc near
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	t|_1 = 7 * 0
	t|_1 = t|_1 + 1
	t|_2 = t|_1 + a
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	t|_1 = stack[t|_2]
	//°°° <location> - structure °°°°
	t|_2 = 7 * 1
	t|_2 = t|_2 + 1
	t|_3 = t|_2 + a
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[t|_3] = t|_1
//-----------------------------------------
E_estructura_$:
	ret
estructura endp
//*******************************************


//*******************************************
//Método: estructura2
//Tipo de retorno: void
//j = stack[]
//b = stack[1]
//t|_1 = stack[24]
//t|_2 = stack[25]
//t|_3 = stack[26]
//*******************************************
estructura2 proc near
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	t|_1 = 1 * 7
	t|_1 = t|_1 + 1
	t|_2 = t|_1 + a
	t|_1 = 1 + t|_2
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[t|_1] = 1
//-----------------------------------------
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	t|_1 = 1 * 7
	t|_1 = t|_1 + 1
	t|_2 = 0 * 1
	t|_2 = t|_2 + 1
	t|_3 = t|_1 + t|_2
	t|_1 = 1 + t|_3
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[t|_1] = 'a'
//-----------------------------------------
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	t|_1 = 1 + b
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	t|_1 = stack[t|_1]
	//°°° <location> - structure °°°°
	t|_2 = 2 * 7
	t|_2 = t|_2 + 1
	t|_3 = t|_2 + a
	t|_2 = 1 + t|_3
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	t|_2 = stack[t|_2]
	t|_3 = 1 * 5
	t|_3 = t|_3 + 6
	t|_2 = t|_2 + t|_3
	t|_1 = t|_1 + t|_2
	stack[] = t|_1
//-----------------------------------------
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	t|_1 = 1 + b
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	t|_1 = stack[t|_1]
	t|_1 = t|_1 + 1
	stack[] = t|_1
//-----------------------------------------
E_estructura2_$:
	ret
estructura2 endp
//*******************************************


//*******************************************
//Método: factorial
//Tipo de retorno: int
//param: n = stack[]
//t|_1 = stack[1]
//t|_2 = stack[2]
//t|_3 = stack[3]
//t|_4 = stack[4]
//*******************************************
factorial proc near
//### if(<exp>) <block1> else <block2> ####
	//=== <boolean exp> (or) ========
	t|_1 = stack[]
	if t|_1 == 1 goto E_factorial_2
	t|_1 = 0
	goto E_factorial_3
E_factorial_2:
	t|_1 = 1
E_factorial_3:
	t|_2 = stack[]
	if t|_2 == 0 goto E_factorial_4
	t|_2 = 0
	goto E_factorial_5
E_factorial_4:
	t|_2 = 1
E_factorial_5:
	t|_1 = t|_1 or t|_2
	//===============================
	if t|_1 == 0 goto E_factorial_0
//### <block1> ############################
	return 1
	goto E_factorial_$
	goto E_factorial_1
E_factorial_0:
//### <block2> - else #####################
	t|_2 = stack[]
	//+++ <metodoCall> ++++++++++++++
	t|_4 = stack[]
	t|_4 = t|_4 - 1
	param t|_4, factorial, 1
	t|_3 = call factorial, 1
	//+++++++++++++++++++++++++++++++
	t|_2 = t|_2 * t|_3
	return t|_2
	goto E_factorial_$
E_factorial_1:
//#########################################
E_factorial_$:
	ret
factorial endp
//*******************************************


//*******************************************
//Método: fibonacci
//Tipo de retorno: int
//param: num = stack[]
//t|_1 = stack[1]
//t|_2 = stack[2]
//t|_3 = stack[3]
//t|_4 = stack[4]
//*******************************************
fibonacci proc near
//### if(<exp>) <block1> else <block2> ####
	//=== <boolean exp> (or) ========
	t|_1 = stack[]
	if t|_1 == 1 goto E_fibonacci_2
	t|_1 = 0
	goto E_fibonacci_3
E_fibonacci_2:
	t|_1 = 1
E_fibonacci_3:
	t|_2 = stack[]
	if t|_2 == 2 goto E_fibonacci_4
	t|_2 = 0
	goto E_fibonacci_5
E_fibonacci_4:
	t|_2 = 1
E_fibonacci_5:
	t|_1 = t|_1 or t|_2
	//===============================
	if t|_1 == 0 goto E_fibonacci_0
//### <block1> ############################
	return 1
	goto E_fibonacci_$
	goto E_fibonacci_1
E_fibonacci_0:
//### <block2> - else #####################
	//+++ <metodoCall> ++++++++++++++
	t|_3 = stack[]
	t|_3 = t|_3 - 1
	param t|_3, fibonacci, 1
	t|_2 = call fibonacci, 1
	//+++++++++++++++++++++++++++++++
	//+++ <metodoCall> ++++++++++++++
	t|_4 = stack[]
	t|_4 = t|_4 - 2
	param t|_4, fibonacci, 1
	t|_3 = call fibonacci, 1
	//+++++++++++++++++++++++++++++++
	t|_2 = t|_2 + t|_3
	return t|_2
	goto E_fibonacci_$
E_fibonacci_1:
//#########################################
E_fibonacci_$:
	ret
fibonacci endp
//*******************************************


//*******************************************
//Método: Ackermann
//Tipo de retorno: int
//param: m = stack[]
//param: n = stack[1]
//t|_1 = stack[2]
//t|_2 = stack[3]
//t|_3 = stack[4]
//t|_4 = stack[5]
//*******************************************
Ackermann proc near
//### if(<exp>) <block1> ##################
	t|_1 = stack[]
	if t|_1 == 0 goto E_Ackermann_1
	t|_1 = 0
	goto E_Ackermann_2
E_Ackermann_1:
	t|_1 = 1
E_Ackermann_2:
	if t|_1 == 0 goto E_Ackermann_0
	t|_2 = stack[1]
	t|_2 = t|_2 + 1
	return t|_2
	goto E_Ackermann_$
E_Ackermann_0:
//#########################################
//### if(<exp>) <block1> ##################
	//=== <boolean exp> (and) =======
	t|_1 = stack[]
	if t|_1 > 0 goto E_Ackermann_4
	t|_1 = 0
	goto E_Ackermann_5
E_Ackermann_4:
	t|_1 = 1
E_Ackermann_5:
	t|_2 = stack[1]
	if t|_2 == 0 goto E_Ackermann_6
	t|_2 = 0
	goto E_Ackermann_7
E_Ackermann_6:
	t|_2 = 1
E_Ackermann_7:
	t|_1 = t|_1 and t|_2
	//===============================
	if t|_1 == 0 goto E_Ackermann_3
	//+++ <metodoCall> ++++++++++++++
	t|_3 = stack[]
	t|_3 = t|_3 - 1
	param t|_3, Ackermann, 1
	param 1, Ackermann, 2
	t|_2 = call Ackermann, 2
	//+++++++++++++++++++++++++++++++
	return t|_2
	goto E_Ackermann_$
E_Ackermann_3:
//#########################################
//### if(<exp>) <block1> ##################
	//=== <boolean exp> (and) =======
	t|_1 = stack[]
	if t|_1 > 0 goto E_Ackermann_9
	t|_1 = 0
	goto E_Ackermann_10
E_Ackermann_9:
	t|_1 = 1
E_Ackermann_10:
	t|_2 = stack[1]
	if t|_2 > 0 goto E_Ackermann_11
	t|_2 = 0
	goto E_Ackermann_12
E_Ackermann_11:
	t|_2 = 1
E_Ackermann_12:
	t|_1 = t|_1 and t|_2
	//===============================
	if t|_1 == 0 goto E_Ackermann_8
	//+++ <metodoCall> ++++++++++++++
	t|_3 = stack[]
	t|_3 = t|_3 - 1
	param t|_3, Ackermann, 1
	//+++ <metodoCall> ++++++++++++++
	t|_4 = stack[]
	param t|_4, Ackermann, 1
	t|_4 = stack[1]
	t|_4 = t|_4 - 1
	param t|_4, Ackermann, 2
	t|_3 = call Ackermann, 2
	//+++++++++++++++++++++++++++++++
	param t|_3, Ackermann, 2
	t|_2 = call Ackermann, 2
	//+++++++++++++++++++++++++++++++
	return t|_2
	goto E_Ackermann_$
E_Ackermann_8:
//#########################################
	return 0
	goto E_Ackermann_$
E_Ackermann_$:
	ret
Ackermann endp
//*******************************************


