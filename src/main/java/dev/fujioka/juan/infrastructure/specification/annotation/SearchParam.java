package dev.fujioka.juan.infrastructure.specification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dev.fujioka.juan.infrastructure.specification.Operacao;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchParam {
	
	public String propriedade() default "";

	public Operacao operacao() default Operacao.EQUALS;

}
