;UNIVERSIDAD DEL VALLE DE GUATEMALA
;CONSTRUCCION DE COMPILADORES
;MARTIN GUZMAN - 08041
;PROYECTO FINAL

.MODEL	LARGE
.STACK	5000h
;-----------------------------------------
.DATA

GP	DW	53 DUP(0)	; ALL GLOBAL VAR
TO_PRINT	DB	5 DUP(0), '$'	; NUMBER TO PRINT
ENTRAR	DB	0DH, 0AH, '$'

;-----------------------------------------

.CODE


;#############################################################

START	PROC	NEAR
	.STARTUP
	XOR	AX, AX
	XOR	BX, BX
	XOR	CX, CX
	XOR	DX, DX

	;--- METHOD CALL -------------------------
	PUSH	0d	;ESPACIO PARA DIRECCION DE RETORNO
	;ESPACIO PARA DATOS LOCALES - 0
	;ESPACIO PARA PARAMETROS - 0
	CALL	main
	;-----------------------------------------

	MOV	AH, 4Ch
	INT	21h

START	ENDP
;#############################################################


;#############################################################

PRINT	PROC	NEAR

	;--- corregir direccion ret ----
	POP	DX
	MOV	BX, SP
	MOV	[SS:BX+2], DX
	;-------------------------------

	MOV	BX, SP
	MOV	BX, [SS:BX+0]

	MOV	DL, 10d
	MOV	CX, 5d
	MOV	DI, 4d
PRI$1:
	XOR	AX, AX
	MOV	AX, BX
	DIV	DL

	ADD	AH, 48d
	MOV	[TO_PRINT+DI], AH
	XOR	AH, AH
	MOV	BX, AX
	ADD	BX, 0d
	JZ	PRI$2
	DEC	DI
	LOOP	PRI$1
PRI$2:
	LEA	DX, [TO_PRINT+DI]

	MOV	AH, 09h
	INT	21H

	LEA	DX, [ENTRAR]

	MOV	AH, 09h
	INT	21H

;POP DEL REGISTRO DE ACTIVACION
	POP	DX
	RET
PRINT	ENDP
;#############################################################




;#############################################################
;Method: tryout
;	params - 4
;	Local Data - 0
;	Temporal - 4
;	Dir ret - 2
;	Val ret - int - 2
;#############################################################
tryout	PROC	NEAR

	;--------------------------
	POP	DX
	MOV	DI, SP
	MOV	[SS:DI+8], DX
	;--------------------------

;--- <assign> ----------------------------
	;°°° <location> - structure °°°°
	MOV	DX, 0	;DX <- 0
	MOV	CX, 2	;CX <- 2
	ADD	CX, DX
	MOV	[SS:DI+4], CX	;t$_1 <- CX
	MOV	DX, [SS:DI+4]	;DX <- t$_1
	MOV	CX, 22	;CX <- 22
	ADD	CX, DX
	MOV	[SS:DI+6], CX	;t$_2 <- CX
	;°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	MOV	AX, 1	;AX <- 1
	MOV	BX, [SS:DI+6]
	MOV	GP[BX], AX	;global[t$_2] <- AX
;-----------------------------------------
;--- <assign> ----------------------------
	;°°° <location> - structure °°°°
	MOV	CX, 2	;CX <- 2
	MOV	AX, 5	;AX <- 5
	MUL	CX
	XOR	DX, DX
	MOV	[SS:DI+4], AX	;t$_1 <- AX
	MOV	DX, 2	;DX <- 2
	MOV	CX, [SS:DI+4]	;CX <- t$_1
	ADD	CX, DX
	MOV	[SS:DI+4], CX	;t$_1 <- CX
	MOV	DX, [SS:DI+4]	;DX <- t$_1
	MOV	CX, 6	;CX <- 6
	ADD	CX, DX
	MOV	[SS:DI+6], CX	;t$_2 <- CX
	;°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	MOV	AX, 0	;AX <- 0
	MOV	BX, [SS:DI+6]
	MOV	GP[BX], AX	;global[t$_2] <- AX
;-----------------------------------------
	MOV	[SS:DI+10], WORD PTR 1	;return_dir <- 1
	JMP	L_tryout_end

	;--- METHOD END'S ---------
L_tryout_end:
	ADD	SP, 8d	;to POP
	RET
tryout	ENDP

;#############################################################




;#############################################################
;Method: tryout$0
;	params - 2
;	Local Data - 0
;	Temporal - 4
;	Dir ret - 2
;	Val ret - char - 2
;#############################################################
tryout$0	PROC	NEAR

	;--------------------------
	POP	DX
	MOV	DI, SP
	MOV	[SS:DI+6], DX
	;--------------------------

;--- <assign> ----------------------------
	;°°° <location> - structure °°°°
	MOV	CX, 1	;CX <- 1
	MOV	AX, 16	;AX <- 16
	MUL	CX
	XOR	DX, DX
	MOV	[SS:DI+2], AX	;t$_1 <- AX
	MOV	DX, 58	;DX <- 58
	MOV	CX, [SS:DI+2]	;CX <- t$_1
	ADD	CX, DX
	MOV	[SS:DI+2], CX	;t$_1 <- CX
	MOV	DX, 0	;DX <- 0
	MOV	CX, [SS:DI+2]	;CX <- t$_1
	ADD	CX, DX
	MOV	[SS:DI+4], CX	;t$_2 <- CX
	;°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	MOV	AX, 2	;AX <- 2
	MOV	BX, [SS:DI+4]
	MOV	GP[BX], AX	;global[t$_2] <- AX
;-----------------------------------------
	MOV	[SS:DI+8], WORD PTR 'c'	;return_dir <- 'c'
	JMP	L_tryout$0_end

	;--- METHOD END'S ---------
L_tryout$0_end:
	ADD	SP, 6d	;to POP
	RET
tryout$0	ENDP

;#############################################################




;#############################################################
;Method: getBool
;	params - 0
;	Local Data - 8
;	Temporal - 4
;	Dir ret - 2
;	Val ret - boolean - 2
;#############################################################
getBool	PROC	NEAR

	;--------------------------
	POP	DX
	MOV	DI, SP
	MOV	[SS:DI+12], DX
	;--------------------------

;--- <assign> ----------------------------
	;--- MethodCall - tryout -----------------
	PUSH	0d	;for the return value
	PUSH	0d	;for the return dir
	PUSH	0d	;for t$_2
	PUSH	0d	;for t$_1
	; local data space - 0
	; parameters space - 2
	PUSH	2	;param literal - tryout - 1
	MOV	DX, 1	;DX <- 1
	MOV	CX, 1	;CX <- 1
	ADD	CX, DX
	MOV	[SS:DI+10], CX	;t$_2 <- CX
	MOV	DX, [SS:DI+10]	;DX <- t$_2
	PUSH	DX	;param temporal - tryout - 2

	CALL	tryout
	POP	DX	;return value
	MOV	DI, SP	;return DI to this method
	MOV	[SS:DI+8], DX	;t$_1 <- DX
	;--- methodCall end - tryout -------------
	MOV	AX, [SS:DI+8]	;AX <- t$_1
	MOV	[SS:DI+0], AX	;stack[0] <- AX
;-----------------------------------------
;--- <assign> ----------------------------
	MOV	AX, 'c'	;AX <- 'c'
	MOV	[SS:DI+6], AX	;stack[6] <- AX
;-----------------------------------------
;--- <assign> ----------------------------
	;--- MethodCall - getBool ----------------
	PUSH	0d	;for the return value
	PUSH	0d	;for the return dir
	PUSH	0d	;for t$_2
	PUSH	0d	;for t$_1
	; local data space - 4
	PUSH	0d
	PUSH	0d
	PUSH	0d
	PUSH	0d
	; parameters space - 0

	CALL	getBool
	POP	DX	;return value
	MOV	DI, SP	;return DI to this method
	MOV	[SS:DI+8], DX	;t$_1 <- DX
	;--- methodCall end - getBool ------------
	;°°° <location> - array °°°°°°°°
	MOV	CX, 1	;CX <- 1
	MOV	AX, 2	;AX <- 2
	MUL	CX
	XOR	DX, DX
	MOV	[SS:DI+10], AX	;t$_2 <- AX
	MOV	DX, 2	;DX <- 2
	MOV	CX, [SS:DI+10]	;CX <- t$_2
	ADD	CX, DX
	MOV	[SS:DI+10], CX	;t$_2 <- CX
	;°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	MOV	AX, [SS:DI+8]	;AX <- t$_1
	MOV	BX, [SS:DI+10]
	MOV	[SS:DI+BX], AX	;stack[t$_2] <- AX
;-----------------------------------------
;### if(<exp>) <block1> else <block2> ####
	MOV	AX, 1	;AX <- 1
	MOV	CX, 2	;CX <- 2
	CMP	AX, CX
	JL	L_getBool_4	;<
	JMP	L_getBool_5
L_getBool_4:
	JMP	L_getBool_2
L_getBool_5:
	MOV	AX, 0	;AX <- 0
	MOV	[SS:DI+8], AX	;t$_1 <- AX
	JMP	L_getBool_3
L_getBool_2:
	MOV	AX, 1	;AX <- 1
	MOV	[SS:DI+8], AX	;t$_1 <- AX
L_getBool_3:
	MOV	AX, [SS:DI+8]	;AX <- t$_1
	MOV	CX, 0	;CX <- 0
	CMP	AX, CX
	JE	L_getBool_6	;==
	JMP	L_getBool_7
L_getBool_6:
	JMP	L_getBool_0
L_getBool_7:
;### <block1> ############################
;--- <assign> ----------------------------
	MOV	AX, 1	;AX <- 1
	MOV	[SS:DI+0], AX	;stack[0] <- AX
;-----------------------------------------
	MOV	[SS:DI+14], WORD PTR 1	;return_dir <- 1
	JMP	L_getBool_end
	JMP	L_getBool_1
L_getBool_0:
;### <block2> - else #####################
	MOV	[SS:DI+14], WORD PTR 0	;return_dir <- 0
	JMP	L_getBool_end
L_getBool_1:
;#########################################

	;--- METHOD END'S ---------
L_getBool_end:
	ADD	SP, 12d	;to POP
	RET
getBool	ENDP

;#############################################################




;#############################################################
;Method: main
;	params - 0
;	Local Data - 0
;	Temporal - 0
;	Dir ret - 2
;	Val ret - void - 0
;#############################################################
main	PROC	NEAR

	;--------------------------
	POP	DX
	MOV	DI, SP
	MOV	[SS:DI+0], DX
	;--------------------------


	;--- METHOD END'S ---------
L_main_end:
	ADD	SP, 0d	;to POP
	RET
main	ENDP

;#############################################################



;-----------------------------------------
END	START
