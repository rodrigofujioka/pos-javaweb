package dev.fujioka.juan.infrastructure.util;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtils {

	public static String[] validandoProPriedadesNulas(Object source) {
		final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
		return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
	}

	public static void copyProperties(Object source, Object target) {
		org.springframework.beans.BeanUtils.copyProperties(source, target, validandoProPriedadesNulas(source));
	}

}
