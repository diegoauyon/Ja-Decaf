//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//               Ámbito global
//a = global[a]
//b = global[b]
//c = global[c]
//vs2 = global[vs2]
//prueba2 = global[prueba2]
//d = global[d]
//vs1 = global[vs1]
//array = global[array]
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//*******************************************
//Método: tryout
//Tipo de retorno: int
//param: t1 = stack[0]
//param: t3 = stack[1]
//*******************************************
tryout proc near
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	global[prueba2].prueba.sa = 1
//-----------------------------------------
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	global[vs2]{5} = 0
//-----------------------------------------
	return 1
	goto E_tryout_$
E_tryout_$:
	ret
tryout endp
//*******************************************


//*******************************************
//Método: tryout|0
//Tipo de retorno: char
//param: t2 = stack[0]
//*******************************************
tryout|0 proc near
//--- <assign> ----------------------------
	//°°° <location> - structure °°°°
// <simpleLocation> ::= id '[' <expression> ']'
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	global[array].sa = 2
//-----------------------------------------
	return 'c'
	goto E_tryout|0_$
E_tryout|0_$:
	ret
tryout|0 endp
//*******************************************


//*******************************************
//Método: getBool
//Tipo de retorno: boolean
//methodb = stack[0]
//methoda = stack[1]
//methodc = stack[2]
//t|_1 = stack[3]
//t|_2 = stack[4]
//*******************************************
getBool proc near
//--- <assign> ----------------------------
	//+++ <metodoCall> ++++++++++++++
	t|_2 = 1 + 1
	param t|_2, tryout, 1
	param 2, tryout, 2
	t|_1 = call tryout, 2
	//+++++++++++++++++++++++++++++++
	stack[1] = t|_1
//-----------------------------------------
//--- <assign> ----------------------------
	stack[2] = 'c'
//-----------------------------------------
//--- <assign> ----------------------------
	//+++ <metodoCall> ++++++++++++++
	t|_1 = call getBool, 0
	//+++++++++++++++++++++++++++++++
	//°°° <location> - array °°°°°°°°
	//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	stack[0]{32} = t|_1
//-----------------------------------------
//### if(<exp>) <block1> else <block2> ####
	if 1 < 2 goto E_getBool_2
	t|_1 = 0
	goto E_getBool_3
E_getBool_2:
	t|_1 = 1
E_getBool_3:
	if t|_1 == 0 goto E_getBool_0
//### <block1> ############################
//--- <assign> ----------------------------
	stack[1] = 1
//-----------------------------------------
	return 1
	goto E_getBool_$
	goto E_getBool_1
E_getBool_0:
//### <block2> - else #####################
	return 0
	goto E_getBool_$
E_getBool_1:
//#########################################
E_getBool_$:
	ret
getBool endp
//*******************************************


//*******************************************
//Método: main
//Tipo de retorno: void
//*******************************************
main proc near
E_main_$:
	ret
main endp
//*******************************************


