.class  public synchronized Ejemplo
.super java/lang/Object


.method public <init>()V
	.limit locals 1
	.limit stack 1

	aload_0
	invokenonvirtual	java/lang/Object.<init>()V
	return
.end method

.method public mundo(IZCLjava/lang/String;)V
	.limit locals 5
	.limit stack 0

	return
.end method

.method public llamadordeMundo()V
	.limit locals 3
	.limit stack 5

	iconst_1
	istore_1
	ldc	"supercaliposato"
	astore_2
	aload_0
	iconst_2
	iload_1
	bipush	99
	aload_2
	invokevirtual	Ejemplo.mundo(IZCLjava/lang/String;)V
	return
.end method
